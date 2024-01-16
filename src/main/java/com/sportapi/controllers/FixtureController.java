package com.sportapi.controllers;

import com.sportapi.model.DTO.FixtureDTO;
import com.sportapi.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
public class FixtureController {

    private final FixtureService fixtureService;

    @Autowired
    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @GetMapping
    public ResponseEntity<List<FixtureDTO>> getAllFixtures() {
        List<FixtureDTO> fixtures = fixtureService.getAllFixtures();
        return new ResponseEntity<>(fixtures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixtureDTO> getFixtureById(@PathVariable Long id) {
        FixtureDTO fixture = fixtureService.getFixtureById(id);
        if (fixture != null) {
            return new ResponseEntity<>(fixture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createFixture(@RequestBody FixtureDTO fixtureDTO) {
        fixtureService.createFixture(fixtureDTO);
        return new ResponseEntity<>("Fixture created successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFixture(@PathVariable Long id, @RequestBody FixtureDTO fixtureDTO) {
        fixtureService.updateFixture(id, fixtureDTO);
        return new ResponseEntity<>("Fixture updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFixture(@PathVariable Long id) {
        fixtureService.deleteFixture(id);
        return new ResponseEntity<>("Fixture deleted successfully", HttpStatus.OK);
    }

    // Other methods as needed
}
