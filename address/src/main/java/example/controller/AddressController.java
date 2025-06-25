package example.controller;

import example.infra.AddressRepository;
import example.model.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public List<Address> getAddress(@RequestParam Long clientId) {
        return addressRepository.getAll()
                .stream()
                .filter(address -> address.getId().equals(clientId))
                .collect(Collectors.toList());
    }
}
