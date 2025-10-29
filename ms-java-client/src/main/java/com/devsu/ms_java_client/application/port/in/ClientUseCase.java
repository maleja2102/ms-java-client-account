package com.devsu.ms_java_client.application.port.in;

import com.devsu.ms_java_client.application.dto.ClientDTO;
import java.util.List;

public interface ClientUseCase {

    ClientDTO createClient(ClientDTO dto);

    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO updateClient(Long id, ClientDTO dto);

    void deleteClient(Long id);
}
