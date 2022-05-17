package de.victor.eventadvisor.service.impl;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.repository.EventRepository;
import de.victor.eventadvisor.service.EventService;
import de.victor.eventadvisor.service.PublicApiService;
import de.victor.eventadvisor.service.publicApiResponse.Feature;
import de.victor.eventadvisor.service.publicApiResponse.PublicApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PublicApiService publicApiService;

    @Override
    public void processNewRemoteValues(PublicApiResponse result) {
        if (result != null) {
            List<Event> toBeSaved = new ArrayList<>();
            for (Feature featurePiece : result.getFeatures()) {
                toBeSaved.add(new Event(featurePiece.getProperties().getData()));
            }
            eventRepository.saveAll(toBeSaved);
        }
    }

    @Override
    public List<Event> findBierEvent() {
        processNewRemoteValues(publicApiService.fetchRemoteValues());
        return eventRepository.findBierEvents("bier");
    }

    @Override
    public List<Event> findByDistrict(String district) {
        processNewRemoteValues(publicApiService.fetchRemoteValues());
        return eventRepository.findAllByBezirkOrderByEventScoreDesc(district);
    }

    @Override
    public void processVote(String eventId, @NotNull String vote) {
        eventRepository.voteEvent(vote.equals("UP") ? 1 : -1, Long.valueOf(eventId));
    }

    @Override
    public List<Event> listBestRankedEvents() {
        return eventRepository.findTop10ByOrderByEventScoreDesc();
    }
}
