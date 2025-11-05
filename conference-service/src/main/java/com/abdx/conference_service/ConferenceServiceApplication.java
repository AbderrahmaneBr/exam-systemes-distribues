package com.abdx.conference_service;

import com.abdx.conference_service.entity.Conference;
import com.abdx.conference_service.feign.KeynoteRestClient;
import com.abdx.conference_service.model.Keynote;
import com.abdx.conference_service.repository.ConferenceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Collection;

@SpringBootApplication
@EnableFeignClients
public class ConferenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ConferenceRepository conferenceRepository, KeynoteRestClient keynoteRestClient) {
		return args -> {

			Instant now = Instant.now();

			// Fetch keynotes from keynote-service
			Collection<Keynote> keynotes = keynoteRestClient.findAllKeynotes().getContent();

			// Example conference data
			Conference conf1 = Conference.builder()
					.name("Tech Innovations 2025")
					.description("Annual technology conference discussing emerging trends in IT and AI")
					.city("Paris")
					.address("1 Rue de Conference, Paris")
					.startDateTime(now.plusSeconds(3600))           // starts in 1 hour
					.endDateTime(now.plusSeconds(3600 * 5))        // lasts 4 hours
					.archived(false)
					.keynoteList(keynotes.stream().toList())
					.build();

			Conference conf2 = Conference.builder()
					.name("Cloud & DevOps Summit")
					.description("Explore the latest in cloud computing, microservices, and DevOps practices")
					.city("Berlin")
					.address("Tech Park 12, Berlin")
					.startDateTime(now.plusSeconds(3600 * 24))      // starts in 24 hours
					.endDateTime(now.plusSeconds(3600 * 28))        // 4-hour event
					.archived(false)
					.keynoteList(keynotes.stream().toList())
					.build();

			Conference conf3 = Conference.builder()
					.name("Cybersecurity Forum")
					.description("International forum covering cybersecurity trends and best practices")
					.city("New York")
					.address("123 Madison Ave, NY")
					.startDateTime(now.plusSeconds(3600 * 48))      // starts in 48 hours
					.endDateTime(now.plusSeconds(3600 * 52))        // 4-hour event
					.archived(false)
					.keynoteList(keynotes.stream().toList())
					.build();

			// Save conferences
			conferenceRepository.save(conf1);
			conferenceRepository.save(conf2);
			conferenceRepository.save(conf3);

			System.out.println("Seeded " + conferenceRepository.count() + " conferences.");
		};
	}

}
