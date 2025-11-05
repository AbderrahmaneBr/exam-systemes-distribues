package com.abdx.conference_service.entity;

import com.abdx.conference_service.model.Keynote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Instant startDateTime;
    private Instant endDateTime;
    private String city;
    private String address;

    @Transient
    private List<Keynote> keynoteList = new ArrayList<>();

    private Boolean archived = false;
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
