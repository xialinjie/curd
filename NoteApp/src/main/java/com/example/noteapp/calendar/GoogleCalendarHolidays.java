package com.example.noteapp.calendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoogleCalendarHolidays {

    private static final String API_KEY = "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"; // Replace with your API key

    public static void main(String[] args) throws GeneralSecurityException, IOException, ParseException {
        String country = "us"; // Replace with the country code you are interested in
        int year = 2023;

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        HttpRequestInitializer httpRequestInitializer = request -> {
            request.getHeaders().set("key", API_KEY);
        };

        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, httpRequestInitializer)
                .setApplicationName("Google Calendar Holidays Example")
                .build();

        String calendarId = "en." + country + "#holiday@group.v.calendar.google.com";

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31");

        Events events = service.events().list(calendarId)
                .setSingleEvents(true)
                .setTimeMin(new com.google.api.client.util.DateTime(startDate))
                .setTimeMax(new com.google.api.client.util.DateTime(endDate))
                .execute();

        List<Event> items = events.getItems();

        for (Event event : items) {
            System.out.println(event.getSummary() + " - " + event.getStart().getDate());
        }
    }
}

