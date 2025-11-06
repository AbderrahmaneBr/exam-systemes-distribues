package com.abdx.conference_service.service.interfaces;

import com.abdx.conference_service.entity.Conference;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface IConferenceService {
    Collection<Conference> getAllConferences();
    Conference getConference(Long id);
}
