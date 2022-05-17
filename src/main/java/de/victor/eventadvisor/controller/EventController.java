package de.victor.eventadvisor.controller;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/find-bier-event")
    public ResponseEntity<List<Event>> fetchBierEvents() {
        try {
            return new ResponseEntity<>(eventService.findBierEvent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/find-by-district/{district}")
    public ResponseEntity<List<Event>> fetchBierEvents(@PathVariable("district") String district) {
        try {
            return new ResponseEntity<>(eventService.findByDistrict(district), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/vote/{eventId}/{vote}")
    public ResponseEntity voteEvent(@PathVariable("eventId") String eventId, @PathVariable("vote") String vote) {
        try {
            eventService.processVote(eventId, vote);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/top-10-events")
    public ResponseEntity<List<Event>> listBestRankedEvents() {
        try {
            return new ResponseEntity<>(eventService.listBestRankedEvents(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
