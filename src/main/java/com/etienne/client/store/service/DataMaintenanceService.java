package com.etienne.client.store.service;

import com.etienne.client.store.MainApplication;
import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.domain.Exam;
import com.etienne.client.store.model.domain.Eye;
import com.etienne.client.store.model.domain.Visit;
import com.etienne.client.store.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataMaintenanceService {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    private final ClientRepository clientRepository;

    public void reloadData() {
        clientRepository.deleteAll();
        importData("clients.csv").forEach(clientRepository::save);
        clientRepository.findAll().forEach(client -> System.out.println("saving " + client.toString()));
    }

    private List<Client> importData(String fileName) {
        List<Client> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                requireNonNull(MainApplication.class.getResourceAsStream("/" + fileName))))) {
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
                        ), "");
                visits.add(visit);
                Client client = new Client(p[0], parseDate(p[2]), p[7].isEmpty() ? p[7] : "06" + p[7], p[8], visits);
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

    private Date parseDate(String strDate) {
        try {
            return "".equals(strDate) ? null : sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
