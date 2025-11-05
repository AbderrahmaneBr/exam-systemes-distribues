package com.abdx.keynote_service.repository;

import com.abdx.keynote_service.entity.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RepositoryRestResource
public interface KeynoteRepository extends JpaRepository<Keynote, Long> {
    List<Keynote> findByConferenceId(Long conferenceId);
}
