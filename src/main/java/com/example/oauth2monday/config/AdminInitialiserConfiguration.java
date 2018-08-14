package com.example.oauth2monday.config;


import com.example.oauth2monday.data.entity.Authority;
import com.example.oauth2monday.data.entity.User;
import com.example.oauth2monday.data.repository.AuthorityRepository;
import com.example.oauth2monday.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitialiserConfiguration {

    /**
     * This Method creates ADMIN role and ADMIN user if they don't already exist
     *
     * @param userRepository
     * @param authorityRepository
     * @param passwordEncoder
     */
    @Bean
    public CommandLineRunner initAdministrator(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (authorityRepository.findOneByName("ADMIN") == null) {
                // create Admin Authority
                Authority authorityAdmin = new Authority("ADMIN");
                authorityRepository.save(authorityAdmin);
            }
            if (authorityRepository.findOneByName("USER") == null) {
                // create User Authority
                Authority authorityUser = new Authority("USER");
                authorityRepository.save(authorityUser);
            }
            if (userRepository.findOneByAuthority(authorityRepository.findOneByName("ADMIN")).size() <= 0) {
                // create Admin account ..
                User admin = new User("ADMIN", "ADMIN", passwordEncoder.encode("ADMIN"), true, authorityRepository.findOneByName("ADMIN"));
                userRepository.save(admin);
            }
            if (userRepository.findOneByAuthority(authorityRepository.findOneByName("USER")).size() <= 0) {
                // create Admin account ..
                User user = new User("USER", "USER", passwordEncoder.encode("USER"), true, authorityRepository.findOneByName("USER"));
                userRepository.save(user);
            }
        };
    }
}
