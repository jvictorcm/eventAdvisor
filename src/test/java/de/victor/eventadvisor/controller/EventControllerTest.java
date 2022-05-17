package de.victor.eventadvisor.controller;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventServiceImpl eventServiceImplMock;

    @Test
    void fetchBierEvent_success() throws Exception {
        List<Event> expectedResult = Collections.emptyList();
        Mockito.when(eventServiceImplMock.findBierEvent()).thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.post("/event/find-bier-event")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }
}