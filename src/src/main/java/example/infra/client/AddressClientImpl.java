package example.infra.client;

import example.model.Address;
import example.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressClientImpl implements AddressClient {

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(AddressClientImpl.class);

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8090/address")
            .queryParam("clientId", "{clientId}")
            .encode()
            .toUriString();

    public AddressClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /*public List<Address> search(Long clientId) {
        return searchAddresses(clientId);
    }*/

    @Override
    public List<Address> searchAddresses(Long clientId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientId);

        logger.info("Searching Addresses");
        final Address[] addresses;

        try {
            addresses = restTemplate.getForObject(API_URL, Address[].class, params);
        } catch (Exception e) {
            logger.error("Error on search address");
            throw e;
        }

        return Arrays.asList(addresses);
    }
}
