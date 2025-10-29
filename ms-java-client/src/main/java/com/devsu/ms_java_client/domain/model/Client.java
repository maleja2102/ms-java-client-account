package com.devsu.ms_java_client.domain.model;

import java.util.Objects;

public final class Client {

    private final Long clientId;
    private final Identity identity;
    private final ContactInformation contactInformation;
    private final String password;
    private final boolean active;

    private Client(Long clientId, Identity identity, ContactInformation contactInformation, String password, boolean active) {
        this.clientId = clientId;
        this.identity = Objects.requireNonNull(identity, "Identity is required");
        this.contactInformation = contactInformation != null ? contactInformation : ContactInformation.empty();
        this.password = requireNonBlank(password, "Password");
        this.active = active;
    }

    public static Client create(Identity identity, ContactInformation contactInformation, String password, boolean active) {
        return new Client(null, identity, contactInformation, password, active);
    }

    public static Client withId(Long clientId, Identity identity, ContactInformation contactInformation, String password, boolean active) {
        return new Client(clientId, identity, contactInformation, password, active);
    }

    public Client assignId(Long clientId) {
        return new Client(clientId, identity, contactInformation, password, active);
    }

    public Client updateWith(Identity identity, ContactInformation contactInformation, String password, boolean active) {
        return new Client(this.clientId, identity, contactInformation, password, active);
    }

    public Long getClientId() {
        return clientId;
    }

    public Identity getIdentity() {
        return identity;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    private static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }
}
