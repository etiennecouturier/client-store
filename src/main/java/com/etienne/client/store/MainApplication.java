package com.etienne.client.store;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ClientRepository repository) {
        Object[][] data = {
                {"Istvan", "1990/03/28", "+36301942154", "istvan@gmail.com"},
                {"Andrew", "1990/03/28", "+36301942155", "andrew@gmail.com"},
                {"Peter", "1990/03/28", "+36301942156", "peter@gmail.com"},
        };

        return args -> {
            repository.deleteAll();
            importData("clients.csv").forEach(repository::save);
            repository.findAll().forEach(client -> System.out.println("saving " + client.toString()));
        };
    }

    private List<Client> importData(String fileName) {
        List<Client> clients = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                requireNonNull(MainApplication.class.getResourceAsStream("/" + fileName))))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\t");
                List<Visit> visits = new ArrayList<>();
                Visit visit = new Visit(p[9], new Eye(parseD(p[10]), parseD(p[11]), parseI(p[12]), parseD(p[13])),
                        new Eye(parseD(p[14]), parseD(p[15]), parseI(p[16]), parseD(p[17])), "");
                visits.add(visit);
                Client client = new Client(p[0], p[2], p[7], p[8], visits);
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

    private Integer parseI(String i) {
        return "".equals(i) ? null : parseInt(i);
    }
}
