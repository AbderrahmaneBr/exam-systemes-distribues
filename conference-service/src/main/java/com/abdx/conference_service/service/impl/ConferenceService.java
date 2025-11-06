package com.abdx.conference_service.service.impl;

import com.abdx.conference_service.entity.Conference;
import com.abdx.conference_service.exception.EmptyDataException;
import com.abdx.conference_service.feign.KeynoteRestClient;
import com.abdx.conference_service.model.Keynote;
import com.abdx.conference_service.repository.ConferenceRepository;
import com.abdx.conference_service.service.interfaces.IConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ConferenceService implements IConferenceService {

    @Autowired
    ConferenceRepository conferenceRepository;
    @Autowired
    KeynoteRestClient keynoteRestClient;

    @Override
    public Collection<Conference> getAllConferences() {
        Collection<Conference> conferences = conferenceRepository.findAll();
        conferences.forEach(conference -> {
            Collection<Keynote> keynotes = keynoteRestClient.findKeynotesByConference(conference.getId());
            conference.setKeynoteList(keynotes.stream().toList());
        });
        return conferences;
    }

    @Override
    public Conference getConference(Long id) {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new EmptyDataException("Conference not found!"));

        List<Keynote> keynotes = keynoteRestClient.findKeynotesByConference(id);
        conference.setKeynoteList(keynotes);

        return conference;
    }
}
