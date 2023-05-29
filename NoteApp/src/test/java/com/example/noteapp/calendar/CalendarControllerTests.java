package com.example.noteapp.calendar;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalendarControllerTests {

    @Autowired
    CalendarController calendarController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getEventByCountry(){
        String region = "US";
        String timeMax = null;
        String timeMin = null;

//        String result = restTemplate.getForEntity("/getEvent");
        String result = calendarController.getEvent(region,timeMax,timeMin);

        assertThat(result,notNullValue());
        assertThat(result, not(""));
//        assertThat(result, anyOf("United States"));
        assertThat(result, containsString("United States"));
    }

    @Test
    public void getEventByCountryAndTime(){
        String region = "US";
        String timeMax = "2023-12-31T10:00:00-07:00";
        String timeMin = "2023-01-01T10:00:00-07:00";

        String result = calendarController.getEvent(region,timeMax,timeMin);

        assertThat(result,notNullValue());
        assertThat(result, not(""));
    }

}
