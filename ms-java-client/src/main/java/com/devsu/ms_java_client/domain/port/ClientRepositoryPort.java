package com.devsu.ms_java_client.domain.port;

import com.devsu.ms_java_client.domain.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {

    Client save(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
