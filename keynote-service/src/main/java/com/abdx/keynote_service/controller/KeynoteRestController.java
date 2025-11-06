package com.abdx.keynote_service.controller;

import com.abdx.keynote_service.entity.Keynote;
import com.abdx.keynote_service.service.impl.KeynoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
public class KeynoteRestController {
    @Autowired
    KeynoteService keynoteService;

    @GetMapping("/conference/{conferenceId}")
    public Collection<Keynote> getKeynotesByConference(Long conferenceId) {
        return keynoteService.findByConferenceId(conferenceId);
    }
}