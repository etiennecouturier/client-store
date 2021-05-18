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
                {"Andrew", 30},
                {"Peter", 100},
                {"Rob", 75}
        };

        return args -> repository
                .deleteAll()
                .thenMany(
                        just(data)
                                .map(array -> new Client((String) array[0], (Integer) array[1]))
                                .flatMap(repository::save)
                )
                .thenMany(repository.findAll())
                .subscribe(client -> System.out.println("saving " + client.toString()));
    }
}
