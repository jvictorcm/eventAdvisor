package de.victor.eventadvisor.controller;

import de.victor.eventadvisor.service.PublicApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private PublicApiService publicApiService;

    @PostMapping(value = "/fetch")
    public void fetchRemoteEvent() {
        publicApiService.fetchRemoteValues();
        System.out.println();
    }

}
