package com.etienne.client.store;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static reactor.core.publisher.Flux.just;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ClientRepository repository) {
        Object[][] data = {
                {"Andrew", 30, 170, "brown", "Hungary", 1028, "Pest", "Budapest", "Deak Ferenc", 16},
                {"Peter", 100, 174, "blue", "Hungary", 1028, "Pest", "Budapest", "Deak Ferenc", 16},
                {"Rob", 75, 150, "brown", "Hungary", 1028, "Pest", "Budapest", "Deak Ferenc", 16}
        };

        return args -> repository
                .deleteAll()
                .thenMany(
                        just(data)
                                .map(array -> new Client((String) array[0], (Integer) array[1], (Integer) array[2], (String) array[3], new Address(
                                        (String) array[4], (Integer) array[5], (String) array[6], (String) array[7], (String) array[8], (Integer) array[9]
                                )))
                                .flatMap(repository::save)
                )
                .thenMany(repository.findAll())
                .subscribe(client -> System.out.println("saving " + client.toString()));
    }
}
