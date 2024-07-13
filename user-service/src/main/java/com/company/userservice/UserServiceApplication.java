package com.company.userservice;

import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserServiceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User(
                "Khadija",
                "Abbasova",
                "77777",
                "khadija@gmail.com",
                "khadija",
                true
        );
        User user2 = new User(
                "Khadija2",
                "Abbasova2",
                "77777",
                "khadija2@gmail.com",
                "khadija2",
                false
        );
        userRepository.saveAll(Arrays.asList(user, user2));
    }
}
