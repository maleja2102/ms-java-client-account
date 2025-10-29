package com.devsu.ms_java_client.infrastructure.persistence.mapper;

import com.devsu.ms_java_client.domain.model.Client;
import com.devsu.ms_java_client.domain.model.ContactInformation;
import com.devsu.ms_java_client.domain.model.Identity;
import com.devsu.ms_java_client.infrastructure.persistence.entity.ClientEntity;

public final class ClientEntityMapper {

    private ClientEntityMapper() {
    }

    public static ClientEntity toEntity(Client client) {
        if (client == null) {
            return null;
        }

        return ClientEntity.builder()
                .clientId(client.getClientId())
                .name(client.getIdentity().name())
                .gender(client.getIdentity().gender())
                .age(client.getIdentity().age())
                .identificationNumber(client.getIdentity().identificationNumber())
                .typeId(client.getIdentity().typeId())
                .address(client.getContactInformation().address())
                .phone(client.getContactInformation().phone())
                .password(client.getPassword())
                .status(client.isActive())
                .build();
    }

    public static Client toDomain(ClientEntity entity) {
        if (entity == null) {
            return null;
        }

        Identity identity = new Identity(
                entity.getName(),
                entity.getGender(),
                entity.getAge(),
                entity.getIdentificationNumber(),
                entity.getTypeId());

        ContactInformation contactInformation = new ContactInformation(entity.getAddress(), entity.getPhone());

        boolean active = Boolean.TRUE.equals(entity.getStatus());

        return Client.withId(entity.getClientId(), identity, contactInformation, entity.getPassword(), active);
    }
}
