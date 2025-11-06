package com.abdx.conference_service.controller;

import com.abdx.conference_service.entity.Conference;
import com.abdx.conference_service.feign.KeynoteRestClient;
import com.abdx.conference_service.model.Keynote;
import com.abdx.conference_service.repository.ConferenceRepository;
import com.abdx.conference_service.service.impl.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/conferences")
@AllArgsConstructor
public class ConferenceRestController {
    @Autowired
    ConferenceService conferenceService;

    @GetMapping
    public Collection<Conference> getAllConferences() {
        return conferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public Conference getConference(@PathVariable Long id) {
        return conferenceService.getConference(id);
    }
}
