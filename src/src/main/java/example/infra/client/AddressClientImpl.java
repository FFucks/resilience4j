package example.infra.client;

import example.model.Address;
import example.model.Client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class AddressClientImpl implements AddressClient {

    private final RestTemplate restTemplate;
    private final AddressFeignClient addressFeignClient;

    private final Logger logger = LoggerFactory.getLogger(AddressClientImpl.class);

    private final Map<Long, List<Address>> CACHE = new HashMap<>();

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8090/address")
            .queryParam("clientId", "{clientId}")
            .encode()
            .toUriString();


    public AddressClientImpl(RestTemplate restTemplate, AddressFeignClient addressFeignClient) {
        this.restTemplate = restTemplate;
        this.addressFeignClient = addressFeignClient;
    }

    @Override
    @CircuitBreaker(name = "addressCB", fallbackMethod = "searchCache")
    public List<Address> searchAddresses(Long clientId) {
        logger.info("Searching Addresses");
        final List<Address> addresses;

        try {
            addresses = addressFeignClient.searchAddresses(clientId);
            //addresses = this.getFromRestTemplate(clientId);

        } catch (Exception e) {
            logger.error("Error on search address");
            throw e;
        }

        logger.info("Caching...");
        CACHE.put(clientId, addresses);

        return addresses;
    }

    private List<Address> getFromRestTemplate(Long clientId) {
        final Map<String, Object> params = new HashMap<>();

        ResponseEntity<List<Address>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Address>>() {},
                params
        );
        params.put("clientId", clientId);
        return response.getBody();
    }

    private List<Address> searchCache(Long clientId, Throwable e) {
        logger.info("Getting from cache");
        return CACHE.getOrDefault(clientId, new ArrayList<>());
    }
}
