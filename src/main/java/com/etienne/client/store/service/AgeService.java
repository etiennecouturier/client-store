package com.etienne.client.store.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.Period.between;
import static java.util.Optional.ofNullable;

@Service
public class AgeService {

    public int calculateAge(LocalDate birthDate) {
        return ofNullable(birthDate)
                .map(date -> between(date, now()).getYears())
                .orElse(0);
    }

}
