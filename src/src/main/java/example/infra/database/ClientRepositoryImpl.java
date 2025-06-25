package example.infra.database;

import example.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    private static final List<Client> CLIENTS = new ArrayList<>();
    private static long id = 1;

    static {
        CLIENTS.add(new Client(nextId(), "Fabio Fucks", "35508748040", "5199998888"));
        CLIENTS.add(new Client(nextId(), "Renato Fucks", "11706462042", "4899991111"));
        CLIENTS.add(new Client(nextId(), "Solo", "71396254099", "4799764356"));
    }

    @Override
    public void save(Client client) {
        client.setId(nextId());
        CLIENTS.add(client);
    }

    @Override
    public Optional<Client> getOne(Long id) {
        return CLIENTS.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(CLIENTS);
    }

    private static long nextId() {
        return id++;
    }
}
