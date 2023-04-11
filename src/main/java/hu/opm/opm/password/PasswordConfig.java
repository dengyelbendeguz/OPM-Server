package hu.opm.opm.password;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PasswordConfig {
    @Bean
    CommandLineRunner passwordCLR(PasswordRepository repository) {
        return args -> {
            Password password1 = new Password(
                    1L,
                    "owner1",
                    "title1",
                    "username1",
                    "password1",
                    "webpage1",
                    "comment1"
            );
            Password password2 = new Password(
                    "owner2",
                    "title2",
                    "username2",
                    "password2",
                    "webpage2",
                    "comment2"
            );
            repository.saveAll(List.of(password1, password2));
        };
    }
}
