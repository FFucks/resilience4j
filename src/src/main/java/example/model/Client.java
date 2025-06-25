package example.model;

import java.util.List;

public class Client {

    private Long id;
    private String name;
    private String document;
    private String phoneNumber;
    private List<Address> addresses;

    public Client() {}

    public Client(Long id, String name, String document, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phoneNumber = phoneNumber;
    }

    public Client(Long id, String name, String document, String phoneNumber, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    public static Client clientOf(Client client, List<Address> addresses) {
        return new Client(
                client.getId(),
                client.getName(),
                client.getDocument(),
                client.getPhoneNumber(),
                addresses
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
