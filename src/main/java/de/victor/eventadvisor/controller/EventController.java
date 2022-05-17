package de.victor.eventadvisor.controller;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "event-rest-controller")
@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(tags = "event-rest-controller", summary = "fetchBierEvents", description = "rest call to return Bier-related Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "failed operation")
    })
    @PostMapping(value = "/find-bier-event", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> fetchBierEvents() {
        try {
            return new ResponseEntity<>(eventService.findBierEvent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(tags = "event-rest-controller", summary = "findByDistrict", description = "rest call to return events related to district")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "failed operation")
    })
    @PostMapping(value = "/find-by-district/{district}")
    public ResponseEntity<List<Event>> findByDistrict(@PathVariable("district") String district) {
        try {
            return new ResponseEntity<>(eventService.findByDistrict(district), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(tags = "event-rest-controller", summary = "voteToEvent", description = "rest call to vote up or down an event, updating its score")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "wrong eventId")
    })
    @PatchMapping(value = "/vote/{eventId}/{vote}")
    public ResponseEntity voteEvent(@PathVariable("eventId") String eventId, @PathVariable("vote") String vote) {
        try {
            eventService.processVote(eventId, vote);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(tags = "event-rest-controller", summary = "getTop10Events", description = "rest call to get best events in town")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "failed operation")
    })
    @GetMapping(value = "/top-10-events")
    public ResponseEntity<List<Event>> getTop10Events() {
        try {
            return new ResponseEntity<>(eventService.listBestRankedEvents(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
