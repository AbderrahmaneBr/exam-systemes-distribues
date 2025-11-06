package com.abdx.keynote_service.service.impl;

import com.abdx.keynote_service.entity.Keynote;
import com.abdx.keynote_service.exception.EmptyDataException;
import com.abdx.keynote_service.repository.KeynoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class KeynoteServiceTest {
    @Mock
    KeynoteRepository keynoteRepository;

    @InjectMocks
    KeynoteService keynoteService;

    Long conferenceId = 1L;


    @Test
    void shouldFindByConferenceId() {
        Collection<Keynote> mockKeynotes = List.of(
                Keynote.builder().id(1L).conferenceId(1L).build(),
                Keynote.builder().id(2L).conferenceId(1L).build());

        Mockito.when(keynoteRepository.findByConferenceId(conferenceId)).thenReturn(mockKeynotes);

        Collection<Keynote> result = keynoteService.findByConferenceId(conferenceId);

        assertEquals(2, result.size());
        Mockito.verify(keynoteRepository).findByConferenceId(conferenceId);
    }

    @Test
    void shouldRaiseExceptionWhenKeynotesEmpty() {
        Mockito.when(keynoteRepository.findByConferenceId(conferenceId)).thenReturn(Collections.emptyList());

        EmptyDataException exception = assertThrows(
                EmptyDataException.class,
                () -> keynoteService.findByConferenceId(conferenceId));

        assertEquals("No keynotes found!", exception.getMessage());
        Mockito.verify(keynoteRepository).findByConferenceId(conferenceId);
    }
}