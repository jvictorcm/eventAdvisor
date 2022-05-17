package de.victor.eventadvisor.service;

import de.victor.eventadvisor.service.publicApiResponse.PublicApiResponse;

public interface PublicApiService {

    PublicApiResponse fetchRemoteValues();
}
