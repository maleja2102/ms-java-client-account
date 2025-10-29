package com.devsu.ms_java_client.application.service;

import com.devsu.ms_java_client.application.dto.ClientDTO;
import com.devsu.ms_java_client.application.mapper.ClientMapper;
import com.devsu.ms_java_client.application.port.in.ClientUseCase;
import com.devsu.ms_java_client.domain.model.Client;
import com.devsu.ms_java_client.domain.port.ClientRepositoryPort;
import com.devsu.ms_java_client.infrastructure.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService implements ClientUseCase {

    private final ClientRepositoryPort clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDTO createClient(ClientDTO dto) {
        Client client = clientMapper.toDomain(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new CustomException("Client not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new CustomException("Client not found with id: " + id, HttpStatus.NOT_FOUND));

        Client updatedData = clientMapper.toDomain(dto);
        Client updatedAggregate = existing.updateWith(
                updatedData.getIdentity(),
                updatedData.getContactInformation(),
                updatedData.getPassword(),
                updatedData.isActive());

        Client updated = clientRepository.save(updatedAggregate);
        return clientMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new CustomException("Client not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }
}
