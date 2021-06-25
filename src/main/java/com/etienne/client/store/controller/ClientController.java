package com.etienne.client.store.controller;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.params.PagingParams;
import com.etienne.client.store.model.params.SortingParams;
import com.etienne.client.store.model.stats.CountPerAge;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService clientService;

    @GetMapping(path = "/filter")
    public Page<Client> filterClients(Client filter,
                                      SortingParams sortingParams,
                                      PagingParams pagingParams) {
        return clientService.filterClients(filter, sortingParams, pagingParams);
    }

    @GetMapping(path = "/id")
    public Client getClientById(String id) throws Exception {
        return clientService.getClientById(id);
    }

    @PostMapping(path = "/new")
    @ResponseStatus(CREATED)
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") String id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/visit-count-last-10-days")
    public List<CountPerDate> getVisitCountForLast10Days() {
        return clientService.getVisitCountForLast10Days();
    }

    @GetMapping("/visitor-count-age")
    public List<CountPerAge> findVisitorCountPerAge() {
        return clientService.getVisitorCountPerAge();
    }

}
