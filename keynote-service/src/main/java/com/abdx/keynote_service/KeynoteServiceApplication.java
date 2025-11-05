package com.abdx.keynote_service;

import com.abdx.keynote_service.entity.Keynote;
import com.abdx.keynote_service.repository.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KeynoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeynoteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KeynoteRepository keynoteRepository) {
		Instant now = Instant.now();
		Instant end = now.plus(Duration.ofHours(3));

	return args -> {
		keynoteRepository.save(Keynote.builder()
				.title("Keynote 1")
				.description("IT discussions")
				.startTime(now)
				.endTime(end)
				.speakerName("Abderrahmane Bourri")
				.speakerBio("Software Engineer")
				.conferenceId(1L)
				.build());

		keynoteRepository.save(Keynote.builder()
				.title("Keynote 2")
				.description("AI Trends and Future of Machine Learning")
				.startTime(now.plus(Duration.ofHours(4)))
				.endTime(now.plus(Duration.ofHours(6)))
				.speakerName("Jane Doe")
				.speakerBio("AI Specialist")
				.conferenceId(2L)
				.build());

		keynoteRepository.save(Keynote.builder()
				.title("Keynote 3")
				.description("Cloud Computing Best Practices")
				.startTime(now.plus(Duration.ofHours(7)))
				.endTime(now.plus(Duration.ofHours(9)))
				.speakerName("John Smith")
				.speakerBio("Cloud Architect")
				.conferenceId(2L)
				.build());

		keynoteRepository.save(Keynote.builder()
				.title("Keynote 4")
				.description("Cybersecurity Challenges in 2025")
				.startTime(now.plus(Duration.ofHours(10)))
				.endTime(now.plus(Duration.ofHours(12)))
				.speakerName("Alice Dupont")
				.speakerBio("Security Expert")
				.conferenceId(1L)
				.build());

		keynoteRepository.save(Keynote.builder()
				.title("Keynote 5")
				.description("The Future of Remote Work")
				.startTime(now.plus(Duration.ofHours(13)))
				.endTime(now.plus(Duration.ofHours(15)))
				.speakerName("Robert Lee")
				.speakerBio("HR Innovator")
				.conferenceId(1L)
				.build());

		keynoteRepository.save(Keynote.builder()
				.title("Keynote 6")
				.description("Sustainable Technology and Green IT")
				.startTime(now.plus(Duration.ofHours(16)))
				.endTime(now.plus(Duration.ofHours(18)))
				.speakerName("Maria Gonzalez")
				.speakerBio("Sustainability Expert")
				.conferenceId(3L)
				.build());
	};
	}
}
