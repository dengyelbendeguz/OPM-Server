package hu.opm.opm.owner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OwnerConfig {
    @Bean
    CommandLineRunner ownerCLR(OwnerRepository repository) {
        return args -> {
            Owner owner1 = new Owner(
                    1L,
                    "owner1username",
                    "owner1password"
            );
            Owner owner2 = new Owner(
                    "owner2username",
                    "owner2password"
            );
            repository.saveAll(List.of(owner1, owner2));
        };
    }
}
