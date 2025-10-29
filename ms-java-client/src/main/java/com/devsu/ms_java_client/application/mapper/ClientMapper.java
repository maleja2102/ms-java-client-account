package com.devsu.ms_java_client.application.mapper;

import com.devsu.ms_java_client.application.dto.ClientDTO;
import com.devsu.ms_java_client.domain.model.Client;
import com.devsu.ms_java_client.domain.model.ContactInformation;
import com.devsu.ms_java_client.domain.model.Identity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toDomain(ClientDTO dto) {
        if (dto == null) {
            return null;
        }

        Identity identity = new Identity(
                dto.getName(),
                dto.getGender(),
                dto.getAge(),
                dto.getIdentificationNumber(),
                dto.getTypeId());

        ContactInformation contactInformation = new ContactInformation(dto.getAddress(), dto.getPhone());

        if (dto.getStatus() == null) {
            throw new IllegalArgumentException("Status is required");
        }

        boolean active = dto.getStatus();

        if (dto.getClientId() != null) {
            return Client.withId(dto.getClientId(), identity, contactInformation, dto.getPassword(), active);
        }

        return Client.create(identity, contactInformation, dto.getPassword(), active);
    }

    public ClientDTO toDto(Client client) {
        if (client == null) {
            return null;
        }

        return ClientDTO.builder()
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
}
