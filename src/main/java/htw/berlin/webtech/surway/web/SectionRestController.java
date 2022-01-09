package htw.berlin.webtech.surway.web;

import htw.berlin.webtech.surway.service.SectionService;
import htw.berlin.webtech.surway.web.api.Section;
import htw.berlin.webtech.surway.web.api.SectionManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SectionRestController {

    private final SectionService sectionService;

    public SectionRestController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(path = "/api/v1/sections")
    public ResponseEntity<List<Section>> fetchSections() {
        return ResponseEntity.ok(sectionService.findAll());
    }

    @PostMapping(path = "/api/v1/sections")
    public ResponseEntity<Void> createSection(@RequestBody SectionManipulationRequest request) throws URISyntaxException {
        var section = sectionService.create(request);
        URI uri = new URI("/api/v1/sections" + section.getId());
        return ResponseEntity.created(uri).build();
    }
}
