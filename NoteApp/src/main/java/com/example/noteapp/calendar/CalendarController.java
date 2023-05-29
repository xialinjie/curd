package com.example.noteapp.calendar;

import com.example.noteapp.calendar.EventDTO;
import com.example.noteapp.calendar.CalendarService;
import com.example.noteapp.subject.SubjectService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


//http://localhost:8080/login/google


@RestController()
public class CalendarController {

    public static final String API_KEY = "AIzaSyBE7v9zj-4JiXQ1_LJgWlG-BuRFvNQQKrE";
    public static final String CALENDAR_ID = "holiday@group.v.calendar.google.com";
    public static final String BASE_CALENDAR_URL = "https://www.googleapis.com/calendar/v3/calendars";

    @Autowired
    CalendarService cSer;

    @Autowired
    private CalendarService calendarService;


    //http://localhost:8080/getEvent?region=US
    //http://localhost:8080/getEvent?region=US&timeMax=2023-12-31T10%3A00%3A00-07%3A00&timeMin=2023-01-01T10%3A00%3A00-07%3A00
    @GetMapping("/getEvent")
    public String getEvent(
            @RequestParam(value = "region", required = true) String region,
            @RequestParam(value = "timeMax", required = false) String timeMax,
            @RequestParam(value = "timeMin", required = false) String timeMin){

        Country country = calendarService.findCodeByName(region);
        String country_code = country.getCode();
        String baseUrl = BASE_CALENDAR_URL + "/" + country_code + "%23" + CALENDAR_ID + "/events?";
        if((timeMax != null && timeMin != null) && (timeMax != "" && timeMin != "")){
            baseUrl = baseUrl + "timeMax="+ timeMax + "&timeMin=" + timeMin +"&key=" + API_KEY;
        }else{
            baseUrl = baseUrl + "key=" + API_KEY;
        }
//        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .encode(false) // Disable automatic encoding
//                .build();
//        String url = uriComponents.toUriString();

        URI uri = URI.create(baseUrl);

        WebClient webClient = WebClient.create();
        String result = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result;
    }

    @GetMapping("/login/google")
    public RedirectView googleConnectionStatusHandler(HttpServletRequest request) throws Exception {
        return cSer.googleConnectionStatus(request);
    }

    @GetMapping(value = "/login/google", params = "code")
    public ResponseEntity<String> oauth2Callback(@RequestParam("code") String code) {
        String msg = cSer.oauth2Callback(code);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getCalendarEventsHandler(
            @RequestParam(value = "startDate", required = true) String startDate,
            @RequestParam(value = "endDate", required = true) String endDate,
            @RequestParam(value = "calendarId1") String calendarId) throws IOException {

        List<Event> events = cSer.getCalendarEvents(startDate, endDate, calendarId);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> scheduleGoogleMeetingHandler(@Valid @RequestBody EventDTO e) throws IOException, GeneralSecurityException {

        String msg = cSer.scheduleGoogleMeeting(e);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteEvent")
    public ResponseEntity<Event> deleteGoogleMeetingHandler(@RequestParam String eventId) throws IOException {
        return new ResponseEntity<>(cSer.deleteGoogleMeeting(eventId), HttpStatus.OK);
    }
    @PutMapping("/updateEvent")
    public ResponseEntity<Event> updateGoogleMeetingHandler(@RequestParam String eventId, @Valid @RequestBody EventDTO e) throws IOException {
        return new ResponseEntity<>(cSer.updateGoogleMeeting(eventId, e), HttpStatus.OK);
    }






}

