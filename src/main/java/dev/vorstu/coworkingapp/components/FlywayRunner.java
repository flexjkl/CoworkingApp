package dev.vorstu.coworkingapp.components;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlywayRunner implements CommandLineRunner {

    private final Flyway flyway;

    //todo справить повторное создание
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Start Flyway migration ===");

        MigrateResult migrationsApplied = flyway.migrate();
        System.out.println("Applied " + migrationsApplied.migrationsExecuted + " migrations");

        System.out.println("=== Applied migrations ===");
        for (MigrationInfo info : flyway.info().applied()) {
            System.out.println(info.getVersion() + " - " + info.getDescription());
        }
    }
}
