package com.voxever.super_chess;

import com.voxever.super_chess.auth.entity.Role;
import com.voxever.super_chess.auth.entity.User;
import com.voxever.super_chess.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SuperChessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperChessApplication.class, args);
    }

    //TODO: REMOVE AFTER DEVELOPMENT - MOCKED USERS
    @Bean
    CommandLineRunner init (UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        return args -> {
            User user1 = User.builder()
                    .username("voxx")
                    .email("p@p.pl")
                    .password(passwordEncoder.encode("123"))
                    .isEnabled(true)
                    .build();

            User user2 = User.builder()
                    .username("voxever")
                    .email("pz@p.pl")
                    .password(passwordEncoder.encode("123"))
                    .isEnabled(true)
                    .build();

            Role role1 = new Role("USER", user1);
            Role role2 = new Role("USER", user2);


            user1.setRoles(Collections.singletonList(role1));
            user2.setRoles(Collections.singletonList(role2));

            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}
