package com.etienne.client.store.controller;

import com.etienne.client.store.model.stats.CountPerAge;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.model.stats.CountPerSex;
import com.etienne.client.store.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/stats")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/visit-count-last-10-days")
    public List<CountPerDate> getVisitCountForLast10Days() {
        return statsService.getVisitCountForLast10Days();
    }

    @GetMapping("/visitor-count-age")
    public List<CountPerAge> findVisitorCountPerAge() {
        return statsService.getVisitorCountPerAge();
    }

    @GetMapping("/visitor-count-sex")
    public List<CountPerSex> findVisitorCountPerSex() {
        return statsService.getVisitorCountPerSex();
    }

}
