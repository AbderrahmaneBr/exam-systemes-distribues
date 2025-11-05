package com.abdx.conference_service.model;

import com.abdx.conference_service.entity.Conference;
import lombok.Data;

import java.time.Instant;

@Data
public class Keynote {
    private Long id;

    private String title;
    private String description;

    private Instant startTime;
    private Instant endTime;

    private String speakerName;
    private String speakerBio;

    private Long conferenceId;
}
