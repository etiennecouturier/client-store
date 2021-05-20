package com.etienne.client.store;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static java.util.Arrays.stream;

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

            stream(data).forEach(array -> repository.save(
                    new Client((String) array[0], (String) array[1], (String) array[2], (String) array[3])));

            repository.findAll().forEach(client -> System.out.println("saving " + client.toString()));
        };
    }
}
