package com.abdx.conference_service.service.impl;

import com.abdx.conference_service.entity.Conference;
import com.abdx.conference_service.exception.EmptyDataException;
import com.abdx.conference_service.feign.KeynoteRestClient;
import com.abdx.conference_service.model.Keynote;
import com.abdx.conference_service.repository.ConferenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConferenceServiceTest {
    @Mock
    ConferenceRepository conferenceRepository;

    @Mock
    KeynoteRestClient keynoteRestClient;

    @InjectMocks
    ConferenceService conferenceService;

    Long conferenceId = 1L;

    @Test
    void shouldGetAllConferences() {
        List<Conference> mockConferences = List.of(Conference.builder().build(), Conference.builder().build());

        Mockito.when(conferenceRepository.findAll()).thenReturn(mockConferences);
        Collection<Conference> result = conferenceService.getAllConferences();

        assertEquals(2, result.size());
        Mockito.verify(conferenceRepository).findAll();

    }

    @Test
    void shouldGetConference() {
        List<Keynote> mockKeynoteList = List.of(Keynote.builder().conferenceId(conferenceId).build(), Keynote.builder().conferenceId(conferenceId).build(), Keynote.builder().conferenceId(conferenceId).build());
        Conference mockConference = Conference.builder().name("Test conf").keynoteList(mockKeynoteList).build();

        Mockito.when(conferenceRepository.findById(conferenceId)).thenReturn(Optional.ofNullable(mockConference));
        Mockito.when(keynoteRestClient.findKeynotesByConference(conferenceId)).thenReturn(mockKeynoteList);

        Conference result = conferenceService.getConference(conferenceId);

        assertEquals("Test conf", result.getName());
        assertEquals(3, result.getKeynoteList().size());
        assertEquals(mockKeynoteList, result.getKeynoteList());

        Mockito.verify(conferenceRepository).findById(conferenceId);
        Mockito.verify(keynoteRestClient).findKeynotesByConference(conferenceId);
    }

    @Test
    void shouldRaiseExceptionWhenConferenceEmpty() {
        Mockito.when(conferenceRepository.findById(conferenceId)).thenReturn(Optional.empty());

        EmptyDataException exception = assertThrows(
                EmptyDataException.class,
                () -> conferenceService.getConference(conferenceId)
        );

        assertEquals("Conference not found!", exception.getMessage());
        Mockito.verify(conferenceRepository).findById(conferenceId);
    }
}