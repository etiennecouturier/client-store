package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.ClientVisit;
import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static java.util.Collections.sort;
import static org.springframework.data.domain.PageRequest.of;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository clientRepository;

    private final NameSexService nameSexService;

    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        log.info("retrieving clients (one page)");
        return clientRepository.findAll(
                clientExample(filter),
                of(pagingParams.getPage(), pagingParams.getSize(), sortingParams.getSorting())
        );
    }

    public Client findClientById(String id) throws ClientNotFoundException {
        log.info("find client by id: " + id);
        return clientRepository.findById(id)
                .map(client -> {
                    sort(client.getVisits());
                    return client;
                })
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    public Client saveClient(Client client) {
        log.info("adding client: " + client);
        nameSexService.enrichClientWithSex(client);
        generateIdForNewVisit(client);
        Client resultClient = clientRepository.save(client);
        sort(resultClient.getVisits());
        return resultClient;
    }

    public void deleteClient(String id) {
        log.info("deleting client: " + id);
        clientRepository.deleteById(id);
    }

    public ClientVisit findClientWithVisit() {
        log.info("find client by visit");
        return clientRepository.findClientWithVisit("");
    }

//    must be done manually
//    mongo only generates id for top level document
    private void generateIdForNewVisit(Client client) {
        client.getVisits()
                .stream()
                .filter(visit -> visit.getId() == null)
                .forEach(visit -> visit.setId(new ObjectId().toString()));
    }

}

