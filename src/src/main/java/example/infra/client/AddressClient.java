package example.infra.client;

import example.model.Address;

import java.util.List;

public interface AddressClient {

    List<Address> searchAddresses(Long clientId);
}
