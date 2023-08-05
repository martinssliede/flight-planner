package io.codelex.flightplanner.configuration;

import io.codelex.flightplanner.Repository.FlightInMemoryRepository;
import io.codelex.flightplanner.Repository.FlightRepository;
import io.codelex.flightplanner.Service.FlightDatabaseService;
import io.codelex.flightplanner.Service.FlightInMemoryService;
import io.codelex.flightplanner.Service.FlightService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {


    @Bean
    @ConditionalOnProperty(prefix = "flight", name = "service.version", havingValue = "in-memory")
    public FlightService getInMemoryVersion(FlightInMemoryRepository flightInMemoryRepository) {
        return new FlightInMemoryService(flightInMemoryRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "flight", name = "service.version", havingValue = "database")
    public FlightService getDatabaseVersion(FlightRepository flightRepository) {
        return new FlightDatabaseService(flightRepository);
    }
}
