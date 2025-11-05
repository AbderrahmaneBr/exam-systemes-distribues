package com.abdx.conference_service.repository;

import com.abdx.conference_service.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
