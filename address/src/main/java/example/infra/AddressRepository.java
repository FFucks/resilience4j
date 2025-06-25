package example.infra;

import example.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    void save(Address avaliacao);
    Optional<Address> getOne(Long clientId);
    List<Address> getAll();
}
