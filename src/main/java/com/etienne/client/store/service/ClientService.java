package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.model.stats.CountPerAge;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.model.stats.CountPerSex;
import com.etienne.client.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static java.util.Collections.sort;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.of;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private static final Map<String, String> sex = new HashMap<>();

    static {
        sex.put("M", "Férfiak");
        sex.put("F", "Nők");
        sex.put("N", "Nem ismert");
    }

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

    public Client getClientById(String id) throws ClientNotFoundException {
        log.info("find client by id: " + id);
        return clientRepository.findById(id)
                .map(client -> {
                    sort(client.getVisits());
                    return client;
                })
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    public Client addClient(Client client) {
        log.info("adding client: " + client);
        nameSexService.enrichClientWithSex(client);
        Client resultClient = clientRepository.save(client);
        sort(resultClient.getVisits());
        return resultClient;
    }

    public void deleteClient(String id) {
        log.info("deleting client: " + id);
        clientRepository.deleteById(id);
    }

    public List<CountPerDate> getVisitCountForLast10Days() {
        log.info("calculating number of visits for last 10 days");
        return clientRepository.findVisitCountForLast10Days();
    }

    public List<CountPerAge> getVisitorCountPerAge() {
        log.info("calculating number of visitors for age categories");
        return clientRepository.findVisitorCountPerAge();
    }

    public List<CountPerSex> getVisitorCountPerSex() {
        log.info("calculating number of visitors based on their sex");
        return clientRepository.findVisitorCountPerSex()
                .stream()
                .peek(e -> e.setSex(sex.get(e.getSex()))).collect(toList());
    }

}

