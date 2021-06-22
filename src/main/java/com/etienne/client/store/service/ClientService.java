package com.etienne.client.store.service;

import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static java.util.Collections.sort;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository clientRepository;

    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        return clientRepository.findAll(
                clientExample(filter),
                of(pagingParams.getPage(), pagingParams.getSize(), sortingParams.getSorting())
        );
    }

    public Client getClientById(String id) throws ClientNotFoundException {
        return clientRepository.findById(id)
                .map(client -> {
                    sort(client.getVisits());
                    return client;
                })
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    public Client addClient(Client client) {
        Client resultClient = clientRepository.save(client);
        sort(resultClient.getVisits());
        return resultClient;
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    public List<CountPerDate> getVisitCountForLast10Days() {
        return clientRepository.findVisitCountForLast10Days();
    }

}

