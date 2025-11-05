package com.abdx.conference_service.feign;

import com.abdx.conference_service.model.Keynote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {

    @GetMapping("/api/keynotes")
    PagedModel<Keynote> findAllKeynotes();

    @GetMapping("/api/keynotes/conference/{conferenceId}")
    List<Keynote> findKeynotesByConference(@PathVariable Long conferenceId);
}
