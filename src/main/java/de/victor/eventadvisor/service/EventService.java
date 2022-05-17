package de.victor.eventadvisor.service;

import de.victor.eventadvisor.model.Event;
import de.victor.eventadvisor.service.publicApiResponse.PublicApiResponse;

import java.util.List;

public interface EventService {

    void processNewRemoteValues(PublicApiResponse result);

    List<Event> findBierEvent();

    List<Event> findByDistrict(String district);

    void processVote(String eventId, String vote);

    List<Event> listBestRankedEvents();
}
