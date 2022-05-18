package de.victor.eventadvisor.controller;

import com.newrelic.api.agent.Trace;
import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.service.EventService;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "event-rest-controller")
@RestController
@RequestMapping(value = "/event")
public class EventController {

    Logger log = LoggerFactory.getLogger(EventController.class);

    SimpleMeterRegistry meterRegistry = new SimpleMeterRegistry();


    @Autowired
    private EventService eventService;

    @Trace(dispatcher = true)
    @Operation(tags = "event-rest-controller", summary = "fetchBierEvents", description = "rest call to return Bier-related Events")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)), @ApiResponse(responseCode = "500", description = "failed operation")})
    @PostMapping(value = "/find-bier-event", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> fetchBierEvents() {
        Timer timer = meterRegistry.timer("controller.event.fetchBierEvents.timer");
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            List<Event> result = eventService.findBierEvent();
            ResponseEntity response = responseBuilder(result, HttpStatus.OK, timer, sample);
            removeMeter("controller.event.fetchBierEvents.timer");
            return response;
        } catch (Exception e) {
            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, timer, sample);
        }
    }

    @Trace(dispatcher = true)
    @Operation(tags = "event-rest-controller", summary = "findByDistrict", description = "rest call to return events related to district")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)), @ApiResponse(responseCode = "500", description = "failed operation")})
    @PostMapping(value = "/find-by-district/{district}")
    public ResponseEntity<List<Event>> findByDistrict(@PathVariable("district") String district) {
        Timer timer = meterRegistry.timer("controller.event.findByDistrict.timer");
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            List<Event> result = eventService.findByDistrict(district);
            ResponseEntity response = responseBuilder(result, HttpStatus.OK, timer, sample);
            removeMeter("controller.event.findByDistrict.timer");
            return response;
        } catch (Exception e) {
            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, timer, sample);
        }
    }

    @Trace(dispatcher = true)
    @Operation(tags = "event-rest-controller", summary = "voteToEvent", description = "rest call to vote up or down an event, updating its score")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)), @ApiResponse(responseCode = "400", description = "wrong eventId")})
    @PatchMapping(value = "/vote/{eventId}/{vote}")
    public ResponseEntity voteEvent(@PathVariable("eventId") String eventId, @PathVariable("vote") String vote) {
        Timer timer = meterRegistry.timer("controller.event.voteEvent.timer");
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            eventService.processVote(eventId, vote);
            removeMeter("controller.event.voteEvent.timer");
            return responseBuilder(HttpStatus.ACCEPTED, timer, sample);
        } catch (Exception e) {
            return responseBuilder(HttpStatus.BAD_REQUEST, timer, sample);
        }
    }

    @Trace(dispatcher = true)
    @Operation(tags = "event-rest-controller", summary = "getTop10Events", description = "rest call to get best events in town")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE)), @ApiResponse(responseCode = "500", description = "failed operation")})
    @GetMapping(value = "/top-10-events")
    public ResponseEntity<List<Event>> getTop10Events() {
        Timer timer = meterRegistry.timer("controller.event.getTop10Events.timer");
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            List<Event> result = eventService.listBestRankedEvents();
            ResponseEntity response = responseBuilder(result, HttpStatus.OK, timer, sample);
            removeMeter("controller.event.getTop10Events.timer");
            return response;
        } catch (Exception e) {
            return responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, timer, sample);
        }
    }


    // Move to helper class
    private void removeMeter(String name) {
        try {
            meterRegistry.remove(meterRegistry.get(name).meter());
        } catch (Exception e) {
            log.info("Error to remove meter", e);
        }
    }

    // Move to helper class
    private ResponseEntity responseBuilder(HttpStatus status, Timer timer, Timer.Sample sample) {
        return responseBuilder(null, status, timer, sample);
    }

    // Move to helper class
    private ResponseEntity responseBuilder(Object responseObject, HttpStatus status, Timer timer, Timer.Sample sample) {
        sample.stop(timer);
        log.info("Response status: " + status);
        log.info("Response time: " + timer.totalTime(TimeUnit.MILLISECONDS));
        log.debug("Response body: " + responseObject);
        if (responseObject == null) {
            return new ResponseEntity(status);
        } else {
            return new ResponseEntity(responseObject, status);
        }
    }
}
