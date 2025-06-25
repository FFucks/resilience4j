package example.infra;

import example.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddressRepositoryImpl implements AddressRepository{
    private static final List<Address> ADDRESSES = new ArrayList<>();
    private static long id = 1;

    static {
        ADDRESSES.add(new Address(nextId(), "Rua São Cristovao", "111",
                "88220000", "Itapema", "SC", "Brasil"));
        ADDRESSES.add(new Address(nextId(), "Rua General Fucks", "556",
                "88034600", "Florianopolis", "SC", "Brasil"));
        ADDRESSES.add(new Address(nextId(), "Rua Belo Monte", "987",
                "88200000", "Tijucas", "SC", "Brasil"));
    }

    @Override
    public void save(Address address) {
        address.setId(nextId());
        ADDRESSES.add(address);
    }

    @Override
    public Optional<Address> getOne(Long clientId) {
        return ADDRESSES.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Address> getAll() {
        return new ArrayList<>(ADDRESSES);
    }

    private static long nextId() {
        return id++;
    }
}
