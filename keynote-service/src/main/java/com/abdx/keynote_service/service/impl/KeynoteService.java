package com.abdx.keynote_service.service.impl;

import com.abdx.keynote_service.entity.Keynote;
import com.abdx.keynote_service.exception.EmptyDataException;
import com.abdx.keynote_service.repository.KeynoteRepository;
import com.abdx.keynote_service.service.interfaces.IKeynoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class KeynoteService implements IKeynoteService {
    @Autowired
    KeynoteRepository keynoteRepository;

    @Override
    public Collection<Keynote> findByConferenceId(Long conferenceId) {
        Collection<Keynote> keynotes = keynoteRepository.findByConferenceId(conferenceId);
        if(keynotes.isEmpty()) {
            throw new EmptyDataException("No keynotes found!");
        }
        return keynotes;
    }
}
