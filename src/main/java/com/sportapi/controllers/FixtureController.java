package com.sportapi.controllers;

import com.sportapi.model.DTO.FixtureDTO;
import com.sportapi.model.Fixture;
import com.sportapi.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
public class FixtureController {

    @Autowired
    private FixtureService fixtureService;

    @GetMapping
    public ResponseEntity<List<Fixture>> getAllFixtures() {
        List<Fixture> fixtures = fixtureService.getAllFixtures();
        return new ResponseEntity<>(fixtures, HttpStatus.OK);
    }

    @GetMapping("/{fixtureId}")
    public ResponseEntity<Fixture> getFixtureById(@PathVariable Long fixtureId) {
        Fixture fixture = fixtureService.getFixtureById(fixtureId);
        if (fixture != null) {
            return new ResponseEntity<>(fixture, HttpStatus.OK);
        } else {
            // Handle fixture not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Fixture> createFixture(@RequestBody FixtureDTO fixtureDTO) {
        try {
            Fixture createdFixture = fixtureService.createFixture(fixtureDTO);
            if (createdFixture != null) {
                return new ResponseEntity<>(createdFixture, HttpStatus.CREATED);
            } else {
                // Handle invalid request or data
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            // Handle invalid data or input
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle internal server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{fixtureId}")
    public ResponseEntity<Fixture> updateFixture(@PathVariable Long fixtureId, @RequestBody FixtureDTO fixtureDTO) {
        try {
            Fixture updatedFixture = fixtureService.updateFixture(fixtureId, fixtureDTO);
            if (updatedFixture != null) {
                return new ResponseEntity<>(updatedFixture, HttpStatus.OK);
            } else {
                // Handle fixture not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            // Handle invalid data or input
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle internal server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{fixtureId}")
    public ResponseEntity<Void> deleteFixture(@PathVariable Long fixtureId) {
        try {
            fixtureService.deleteFixture(fixtureId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            // Handle invalid fixture ID
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle internal server error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
