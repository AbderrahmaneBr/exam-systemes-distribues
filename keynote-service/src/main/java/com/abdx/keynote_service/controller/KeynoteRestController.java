package com.abdx.keynote_service.controller;

import com.abdx.keynote_service.entity.Keynote;
import com.abdx.keynote_service.repository.KeynoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteRestController {
    @Autowired
    KeynoteRepository keynoteRepository;

    @GetMapping("/conference/{conferenceId}")
    public List<Keynote> getKeynotesByConference(@PathVariable Long conferenceId) {
        return keynoteRepository.findByConferenceId(conferenceId);
    }
}