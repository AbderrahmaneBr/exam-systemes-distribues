package com.abdx.conference_service.controller;

import com.abdx.conference_service.entity.Conference;
import com.abdx.conference_service.feign.KeynoteRestClient;
import com.abdx.conference_service.model.Keynote;
import com.abdx.conference_service.repository.ConferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/conferences")
@AllArgsConstructor
public class ConferenceRestController {
    private final ConferenceRepository conferenceRepository;
    private final KeynoteRestClient keynoteRestClient;

    @GetMapping
    public List<Conference> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        conferences.forEach(conference -> {
            List<Keynote> keynotes = keynoteRestClient.findKeynotesByConference(conference.getId());
            conference.setKeynoteList(keynotes);
        });
        return conferences;
    }

    @GetMapping("/{id}")
    public Conference getConference(@PathVariable Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        List<Keynote> keynotes = keynoteRestClient.findKeynotesByConference(id);
        conference.setKeynoteList(keynotes);

        return conference;
    }
}
