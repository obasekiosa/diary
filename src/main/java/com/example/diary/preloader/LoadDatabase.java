package com.example.diary.preloader;

import com.example.diary.model.User;
import com.example.diary.model.UserRelationship;
import com.example.diary.repository.UserRelationshipRepository;
import com.example.diary.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, UserRelationshipRepository userRelationshipRepository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("seki", "seki")));
            log.info("Preloading " + repository.save(new User("hanifa", "hanifa")));
            log.info("Preloading " + userRelationshipRepository.save(new UserRelationship("hanifa", "seki")));
        };
    }
}