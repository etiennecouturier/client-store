package com.etienne.client.store.controller;

import com.etienne.client.store.service.DataMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/data-maintenance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataMaintenanceController {

    private final DataMaintenanceService dataMaintenanceService;

    @GetMapping("/reload-data")
    public void reloadData() {
        dataMaintenanceService.reloadData();
    }

    @GetMapping("/delete-all-data")
    public void deleteAllData() {
        dataMaintenanceService.deleteAllData();
    }

    @GetMapping("/create-users")
    public void createUseres() {
        dataMaintenanceService.createUsers();
    }

}
