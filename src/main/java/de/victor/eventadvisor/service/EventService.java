package de.victor.eventadvisor.service;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.repository.EventRepository;
import de.victor.eventadvisor.service.publicApiResponse.Feature;
import de.victor.eventadvisor.service.publicApiResponse.PublicApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void processNewRemoteValues(PublicApiResponse result) {
        List<Event> toBeSaved = new ArrayList<Event>();
        for (Feature featurePiece : result.getFeatures()) {
            toBeSaved.add(new Event(featurePiece.getProperties().getData()));
        }
        eventRepository.saveAll(toBeSaved);
    }
}
