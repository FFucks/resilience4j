package example.controller;

import example.infra.client.AddressClient;
import example.infra.database.ClientRepository;
import example.model.Address;
import example.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registry")
public class RegistryController {

    private final ClientRepository clientRepository;
    private final AddressClient addressClient;

    public RegistryController(ClientRepository clientRepository, AddressClient addressClient) {
        this.clientRepository = clientRepository;
        this.addressClient = addressClient;
    }

    @GetMapping("/{clientId}")
    public Client buscarPorId(@PathVariable Long clientId) {
        return clientRepository.getOne(clientId)
                .map(this::converterClientAddress)
                .orElseThrow(NotFoundException::new);
    }

    private Client converterClientAddress(Client client) {
        return Client.clientOf(client, searchAddress(client.getId()));
    }

    private List<Address> searchAddress(Long clientId) {
        return addressClient.searchAddresses(clientId);
    }

}
