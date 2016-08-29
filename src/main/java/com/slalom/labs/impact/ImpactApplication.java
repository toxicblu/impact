package com.slalom.labs.impact;

import com.slalom.labs.impact.domain.*;
import com.slalom.labs.impact.service.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ted Hunter <t.hunt750@gmail.com> on 3/10/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ImpactApplication {

    private static final Logger log = LoggerFactory.getLogger(ImpactApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ImpactApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(OrganizationRepository orgRepository, TeamRepository teamRepository,
                                  ServiceRepository serviceRepository, ContractRepository contractRepository,
                                  WebhookRepository webhookRepository) {
        return (args) -> {
            // Save some organizations
            orgRepository.save(new Organization("slalom", "Slalom Consulting", null));
            orgRepository.save(new Organization("acme", "Acme Trading Co", null));
            orgRepository.save(new Organization("maverick-space", "Maveric Space Federation", null));
            orgRepository.save(new Organization("blackwell-insurance", "Blackwell Insurance Co", null));

            // fetch all orgs
            log.info("Organizations found with findAll():");
            log.info("-----------------------------------");
            orgRepository.findAll()
                    .forEach(organization -> log.info(organization.getPresentedName()));
            log.info("");

            // fetch an organization by name
            log.info("Organization found with name('acme'):");
            log.info("-----------------------------------");
            orgRepository.findByName("acme")
                    .forEach(organization -> log.info(organization.getPresentedName()));
            log.info("");


            // Save some teams
            teamRepository.save(new Team(orgRepository.findByName("slalom").get(0),
                    "atl-custom-dev", "Atlanta Custom Dev", null));
            teamRepository.save(new Team(orgRepository.findByName("slalom").get(0),
                    "atl-xsd", "Atlanta XSD", null));
            // fetch all teams
            log.info("Teams found with findAll():");
            log.info("-----------------------------------");
            teamRepository.findAll()
                    .forEach(team -> log.info(team.getPresentedName()));
            log.info("");

            // fetch a team by name
            log.info("Team found with name('atl-custom-dev'):");
            log.info("-----------------------------------");
            teamRepository.findByName("atl-custom-dev")
                    .forEach(team -> log.info(team.getPresentedName()));
            log.info("");

            // see teams in Slalom Internal
            log.info("Slalom's teams:");
            log.info("-----------------------------------");
            orgRepository.findByName("slalom")
                    .stream()
                    .flatMap(l -> l.getTeams().stream())
                    .collect(Collectors.toList())
                    .forEach(team -> log.info(team.getName()));
            log.info("");

            // Save some services
            serviceRepository.save(new Service("service1","Example Service 1", "An example service",
                    teamRepository.findByName("atl-custom-dev").get(0), null, null, null));
            serviceRepository.save(new Service("service2","Example Service 2", "Another example service",
                    teamRepository.findByName("atl-custom-dev").get(0), null, null, null));

            // Save a contract
            Team team = teamRepository.findByName("atl-custom-dev").get(0);
            List<Service> services = team.getServices();
            Contract contract = contractRepository.save(new Contract(services.get(0), services.get(1), null, 300));

            // Save a webhook
            webhookRepository.save(new Webhook(contract, "localhost:8080/testing/mock", "my-secret", false));
        };
    }
}
