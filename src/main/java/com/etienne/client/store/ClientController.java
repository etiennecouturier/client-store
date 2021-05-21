package com.etienne.client.store;

import com.etienne.client.store.model.PagingParams;
import com.etienne.client.store.model.SortingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.etienne.client.store.repository.ClientExample.clientExample;
import static org.springframework.data.domain.PageRequest.of;

@Controller
@RequestMapping(path = "/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientRepository clientRepository;

    @PostMapping()
    public @ResponseBody
    Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping()
    public @ResponseBody
    List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/filter")
    public @ResponseBody
    Page<Client> filterClients(Client filter,
                               SortingParams sortingParams,
                               PagingParams pagingParams) {
        return clientRepository.findAll(
                clientExample(filter),
                of(pagingParams.getPage(), pagingParams.getSize(), sortingParams.getSorting())
        );
    }
}
