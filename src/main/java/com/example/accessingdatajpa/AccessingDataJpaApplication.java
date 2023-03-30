package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args){
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    //ORM 간단 테스트 함수
    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("흥민", "손"));
            repository.save(new Customer("희찬", "황"));
            repository.save(new Customer("민재", "김"));
            repository.save(new Customer("현우", "조"));
            repository.save(new Customer("규성", "조"));

            // fetch all customers
            log.info("findAll() - 전체 고객 리스트:");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(3L);
            log.info("findById(3L) - 3번 고객 정보:");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("findByLastName('조') - 조씨 성을 가진 고객 정보 :");
            log.info("--------------------------------------------");
            repository.findByLastName("조").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }
}
