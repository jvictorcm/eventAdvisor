package de.victor.eventadvisor.service.impl;

import de.victor.eventadvisor.model.PublicApi;
import de.victor.eventadvisor.repository.PublicApiRepository;
import de.victor.eventadvisor.service.PublicApiService;
import de.victor.eventadvisor.service.publicApiResponse.PublicApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PersistenceException;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class PublicApiServiceImpl implements PublicApiService {

    @Value("${public.api.url}")
    private String publicApiUrl;

    @Autowired
    private PublicApiRepository publicApiRepository;

    private final RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(2)).setReadTimeout(Duration.ofSeconds(10)).build();

    @Override
    public PublicApiResponse fetchRemoteValues() {
        if (LocalDateTime.now().minusHours(4).compareTo(publicApiRepository.getLastUpdate()) > 0) {
            try {
                PublicApiResponse result = restTemplate.getForObject(publicApiUrl, PublicApiResponse.class);
                PublicApi currentPublicApi = publicApiRepository.getById(1L);
                currentPublicApi.setUpdatedAt(LocalDateTime.now());
                publicApiRepository.save(currentPublicApi);
                return result;
            } catch (PersistenceException e) {
                throw new PersistenceException();
            } catch (Exception e) {
                //do nothing
            }
        }
        return null;
    }
}
