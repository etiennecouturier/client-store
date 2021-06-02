package com.etienne.client.store;

import com.etienne.client.store.model.Client;
import com.etienne.client.store.model.Eye;
import com.etienne.client.store.model.Visit;
import com.etienne.client.store.repository.ClientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@SpringBootApplication
public class MainApplication {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ClientRepository repository) {
        return args -> {
//            repository.deleteAll();
//            importData("clients.csv").forEach(repository::save);
//            repository.findAll().forEach(client -> System.out.println("saving " + client.toString()));
        };
    }

    private List<Client> importData(String fileName) {
        List<Client> clients = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                requireNonNull(MainApplication.class.getResourceAsStream("/" + fileName))))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                List<Visit> visits = new ArrayList<>();
                Visit visit = new Visit(parseDate(p[9]), new Eye(parseD(p[10]), parseD(p[11]), parseD(p[12]), parseD(p[13])),
                        new Eye(parseD(p[14]), parseD(p[15]), parseD(p[16]), parseD(p[17])), p[18], "");
                visits.add(visit);
                Client client = new Client(p[0], p[2], p[7].isEmpty()? p[7]:"06"+p[7], p[8], visits);
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
