package com.sportapi.services.Impl;

import com.sportapi.model.DTO.FixtureDTO;
import com.sportapi.model.Event;
import com.sportapi.model.Fixture;
import com.sportapi.model.Teams;
import com.sportapi.repositories.FixtureRepository;
import com.sportapi.services.EventService;
import com.sportapi.services.FixtureService;
import com.sportapi.services.TeamsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private TeamsService teamsService;

    @Override
    @Transactional
    public List<Fixture> getAllFixtures() {
        return fixtureRepository.findAll();
    }

    @Override
    @Transactional
    public Fixture getFixtureById(Long fixtureId) {
        return fixtureRepository.findById(fixtureId).orElse(null);
    }

    @Override
    @Transactional
    public Fixture createFixture(FixtureDTO fixtureDTO) {
        Event event = eventService.getEventById(fixtureDTO.getEventId());
        Teams team1 = teamsService.getTeamById(fixtureDTO.getTeam1Id());
        Teams team2 = teamsService.getTeamById(fixtureDTO.getTeam2Id());

        if (event != null && team1 != null && team2 != null) {
            Fixture fixture = new Fixture(event, team1, team2, fixtureDTO.getDateTime(), fixtureDTO.getGender());
            return fixtureRepository.save(fixture);
        } else {
            // Handle invalid event or team IDs
            throw new IllegalArgumentException("Invalid event or team IDs provided");
        }
    }

    @Override
    @Transactional
    public Fixture updateFixture(Long fixtureId, FixtureDTO fixtureDTO) {
        Fixture existingFixture = fixtureRepository.findById(fixtureId).orElse(null);

        if (existingFixture != null) {
            Event event = eventService.getEventById(fixtureDTO.getEventId());
            Teams team1 = teamsService.getTeamById(fixtureDTO.getTeam1Id());
            Teams team2 = teamsService.getTeamById(fixtureDTO.getTeam2Id());

            if (event != null && team1 != null && team2 != null) {
                existingFixture.setEvent(event);
                existingFixture.setTeam1(team1);
                existingFixture.setTeam2(team2);
                existingFixture.setDateTime(fixtureDTO.getDateTime());
                existingFixture.setGender(fixtureDTO.getGender());

                return fixtureRepository.save(existingFixture);
            } else {
                // Handle invalid event or team IDs
                throw new IllegalArgumentException("Invalid event or team IDs provided");
            }
        } else {
            // Handle fixture not found
            throw new IllegalArgumentException("Fixture not found with ID: " + fixtureId);
        }
    }

    @Override
    @Transactional
    public void deleteFixture(Long fixtureId) {
        fixtureRepository.deleteById(fixtureId);
    }

    @Override
    @Transactional
    public List<Teams> getTeamsForFixture(Long fixtureId) {
        Fixture fixture = fixtureRepository.findById(fixtureId).orElse(null);
        if (fixture != null) {
            List<Teams> teams = new ArrayList<>();
            teams.add(fixture.getTeam1());
            teams.add(fixture.getTeam2());
            return teams;
        } else {
            // Handle fixture not found
            throw new IllegalArgumentException("Fixture not found with ID: " + fixtureId);
        }
    }

    // Add other methods as needed

    @Override
    @Transactional
    public void addTeamToFixture(Long fixtureId, Long teamId) {
        Fixture fixture = fixtureRepository.findById(fixtureId).orElse(null);
        Teams team = teamsService.getTeamById(teamId);

        if (fixture != null && team != null) {
            // Check if the team is already in the fixture
            if (!fixture.getTeam1().equals(team) && !fixture.getTeam2().equals(team)) {
                // Add the team to the fixture
                if (fixture.getTeam1() == null) {
                    fixture.setTeam1(team);
                } else if (fixture.getTeam2() == null) {
                    fixture.setTeam2(team);
                }
                fixtureRepository.save(fixture);
            } else {
                // Handle case where the team is already in the fixture
                throw new IllegalStateException("Team with ID " + teamId + " is already in the fixture");
            }
        }
    }

    @Override
    @Transactional
    public void removeTeamFromFixture(Long fixtureId, Long teamId) {
        Fixture fixture = fixtureRepository.findById(fixtureId).orElse(null);

        if (fixture != null) {
            // Remove the team from the fixture
            if (fixture.getTeam1() != null && fixture.getTeam1().getId().equals(teamId)) {
                fixture.setTeam1(null);
            } else if (fixture.getTeam2() != null && fixture.getTeam2().getId().equals(teamId)) {
                fixture.setTeam2(null);
            }
            fixtureRepository.save(fixture);
        }
    }
}
