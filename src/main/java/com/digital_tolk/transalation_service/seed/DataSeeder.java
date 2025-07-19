package com.digital_tolk.transalation_service.seed;

import com.digital_tolk.transalation_service.entity.TranslationDomain;
import com.digital_tolk.transalation_service.repository.TranslationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final TranslationRepository repo;

    public DataSeeder(TranslationRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() > 0) {
            System.out.println("Translations already exist. Skipping seeding...");
            return;
        }

        List<TranslationDomain> translations = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            translations.add(
                    TranslationDomain.builder()
                            .locale("en")
                            .key("key" + i)
                            .value("value" + i)
                            .context("web")
                            .updatedAt(LocalDateTime.now())
                            .build()
            );
        }

        // Save any remaining items
        if (!translations.isEmpty()) {
            repo.saveAll(translations);
        }

        System.out.println("Seeded 100 translations successfully.");
    }
}