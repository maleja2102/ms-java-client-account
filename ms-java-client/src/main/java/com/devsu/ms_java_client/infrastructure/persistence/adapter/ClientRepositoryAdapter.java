package com.devsu.ms_java_client.infrastructure.persistence.adapter;

import com.devsu.ms_java_client.domain.model.Client;
import com.devsu.ms_java_client.domain.port.ClientRepositoryPort;
import com.devsu.ms_java_client.infrastructure.persistence.entity.ClientEntity;
import com.devsu.ms_java_client.infrastructure.persistence.jpa.ClientJpaRepository;
import com.devsu.ms_java_client.infrastructure.persistence.mapper.ClientEntityMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Client save(Client client) {
        ClientEntity entity = ClientEntityMapper.toEntity(client);
        ClientEntity savedEntity = clientJpaRepository.save(entity);
        return ClientEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll().stream()
                .map(ClientEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaRepository.findById(id).map(ClientEntityMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return clientJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        clientJpaRepository.deleteById(id);
    }
}
