package com.abdx.keynote_service.service.interfaces;

import com.abdx.keynote_service.entity.Keynote;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface IKeynoteService {
    Collection<Keynote> findByConferenceId(Long conferenceId);
}
