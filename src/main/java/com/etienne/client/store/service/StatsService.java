package com.etienne.client.store.service;

import com.etienne.client.store.model.stats.CountPerAge;
import com.etienne.client.store.model.stats.CountPerDate;
import com.etienne.client.store.model.stats.CountPerSex;
import com.etienne.client.store.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatsService {

    private static final Map<String, String> sex = new HashMap<>();

    static {
        sex.put("M", "Férfiak");
        sex.put("F", "Nők");
        sex.put("N", "Nem ismert");
    }

    private final StatsRepository statsRepository;

    public List<CountPerDate> getVisitCountForLast10Days() {
        log.info("calculating number of visits for last 10 days");
        return statsRepository.findVisitCountForLast10Days();
    }

    public List<CountPerAge> getVisitorCountPerAge() {
        log.info("calculating number of visitors for age categories");
        return statsRepository.findVisitorCountPerAge();
    }

    public List<CountPerSex> getVisitorCountPerSex() {
        log.info("calculating number of visitors based on their sex");
        return statsRepository.findVisitorCountPerSex()
                .stream()
                .peek(e -> e.setSex(sex.get(e.getSex()))).collect(toList());
    }

}
