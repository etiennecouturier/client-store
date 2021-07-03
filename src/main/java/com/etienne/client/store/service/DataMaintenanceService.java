package com.etienne.client.store.service;

import com.etienne.client.store.MainApplication;
import com.etienne.client.store.model.domain.*;
import com.etienne.client.store.repository.ClientRepository;
import com.etienne.client.store.repository.NameSexRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataMaintenanceService {

    private static final DateTimeFormatter dtf = ofPattern("yyyy/MM/dd");

    private final ClientRepository clientRepository;

    private final NameSexRepository nameSexRepository;

    private final AgeService ageService;

    private final NameSexService nameSexService;

    public void deleteAllData() {
        nameSexRepository.deleteAll();
        clientRepository.deleteAll();
    }

    public void reloadData() {
        nameSexRepository.deleteAll();
        clientRepository.deleteAll();
        importNamesWithSex().forEach(nameSexRepository::save);
        importClients().forEach(clientRepository::save);
        nameSexRepository.findAll().forEach(nameSex -> log.info("saving " + nameSex.toString()));
        clientRepository.findAll().forEach(client -> log.info("saving " + client.toString()));
    }

    private List<NameSex> importNamesWithSex() {
        List<NameSex> nameSexes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                requireNonNull(MainApplication.class.getResourceAsStream("/names_sexes.csv"))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                nameSexes.add(new NameSex(p[0], p[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameSexes;
    }

    private List<Client> importClients() {
        List<Client> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                requireNonNull(MainApplication.class.getResourceAsStream("/clients.csv"))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                List<Visit> visits = new ArrayList<>();
                Visit visit = new Visit(parseDate(p[9]),
                        new Exam(
                                new Eye(null, null, null, null, null),
                                new Eye(null, null, null, null, null),
                                ""
                        ),
                        new Exam(
                                new Eye(parseD(p[10]), parseD(p[11]), parseD(p[12]), parseD(p[13]), null),
                                new Eye(parseD(p[14]), parseD(p[15]), parseD(p[16]), parseD(p[17]), null),
                                p[18]
                        ),
                        new Exam(
                                new Eye(null, null, null, null, null),
                                new Eye(null, null, null, null, null),
                                ""
                        ), "", new Fees());
                visits.add(visit);
                LocalDate dob = parseDate(p[2]);
                Client client = new Client(p[0], dob, ageService.calculateAge(dob), p[7].isEmpty() ? p[7] : "06" + p[7], p[8], visits);
                nameSexService.enrichClientWithSex(client);
                clients.add(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    private Double parseD(String d) {
        return "".equals(d) ? null : parseDouble(d);
    }

    private LocalDate parseDate(String strDate) {
        return "".equals(strDate) ? null : parse(strDate, dtf);
    }

}
