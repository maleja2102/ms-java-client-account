package application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.dto.ClientDTO;
import domain.Client;
import infrastructure.repository.ClientRepository;
import infrastructure.exception.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO createClient(ClientDTO dto) {
        Client client = mapToEntity(dto);
        Client saved = clientRepository.save(client);
        return mapToDTO(saved);
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id){
        return clientRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    public ClientDTO updateClient(Long id, ClientDTO dto){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()){
            throw new CustomException("Client not found with id: " + id);
        }

        Client client = optionalClient.get();
        client.setName(dto.getName());
        client.setGender(dto.getGender());
        client.setAge(dto.getAge());
        client.setIdentificationNumber(dto.getIdentification());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        client.setPassword(dto.getPassword());
        client.setStatus(dto.getStatus());

        Client updated = clientRepository.save(client);
        return mapToDTO(updated);
    }

    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)) throw new CustomException("Client not found with id: " + id);
        else clientRepository.deleteById(id);
    }

    private ClientDTO mapToDTO(Client client) {
        return new ClientDTO(
            client.getClientId(),
            client.getName(),
            client.getGender(),
            client.getAge(),
            client.getIdentificationNumber(),
            client.getAddress(),
            client.getPhone(),
            client.getPassword(),
            client.getStatus()
        );
    }

    private Client mapToEntity(ClientDTO dto){
        Client client = new Client();
        client.setClientId(dto.getClientId());
        client.setName(dto.getName());
        client.setGender(dto.getGender());
        client.setAge(dto.getAge());
        client.setIdentificationNumber(dto.getIdentification());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        client.setPassword(dto.getPassword());
        client.setStatus(dto.getStatus());
        return client;  
    }

}