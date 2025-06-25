package example.infra.database;

import example.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    void save(Client client);
    Optional<Client> getOne(Long clientId);
    List<Client> getAll();
}
