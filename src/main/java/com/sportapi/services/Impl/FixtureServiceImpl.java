package com.sportapi.services.Impl;

import com.sportapi.model.DTO.FixtureDTO;
import com.sportapi.model.Event;
import com.sportapi.model.Fixture;
import com.sportapi.model.Teams;
import com.sportapi.repositories.FixtureRepository;
import com.sportapi.repositories.EventRepository;
import com.sportapi.repositories.TeamsRepository;
import com.sportapi.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FixtureServiceImpl implements FixtureService {

    private final FixtureRepository fixtureRepository;
    private final EventRepository eventRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public FixtureServiceImpl(FixtureRepository fixtureRepository, EventRepository eventRepository, TeamsRepository teamsRepository) {
        this.fixtureRepository = fixtureRepository;
        this.eventRepository = eventRepository;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<FixtureDTO> getAllFixtures() {
        return fixtureRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FixtureDTO getFixtureById(Long fixtureId) {
        return fixtureRepository.findById(fixtureId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public void createFixture(FixtureDTO fixtureDTO) {
        Fixture fixture = convertToEntity(fixtureDTO);
        fixtureRepository.save(fixture);
    }

    @Override
    @Transactional
    public void updateFixture(Long fixtureId, FixtureDTO fixtureDTO) {
        Fixture existingFixture = fixtureRepository.findById(fixtureId).orElse(null);

        if (existingFixture != null) {
            existingFixture.setDateTime(fixtureDTO.getDateTime());
            existingFixture.setGender(fixtureDTO.getGender());

            // Assuming event and teams are not updated for simplicity.
            // If needed, you can add logic to update event and teams.

            fixtureRepository.save(existingFixture);
        }
    }

    @Override
    @Transactional
    public void deleteFixture(Long fixtureId) {
        fixtureRepository.deleteById(fixtureId);
    }

    // Helper method to convert Fixture entity to FixtureDTO
    private FixtureDTO convertToDTO(Fixture fixture) {
        return new FixtureDTO(
                fixture.getId(),
                fixture.getEvent().getId(),
                fixture.getTeam1().getId(),
                fixture.getTeam2().getId(),
                fixture.getDateTime(),
                fixture.getGender()
        );
    }

    // Helper method to convert FixtureDTO to Fixture entity
    private Fixture convertToEntity(FixtureDTO fixtureDTO) {
        Event event = eventRepository.findById(fixtureDTO.getEventId()).orElse(null);
        Teams team1 = teamsRepository.findById(fixtureDTO.getTeam1Id()).orElse(null);
        Teams team2 = teamsRepository.findById(fixtureDTO.getTeam2Id()).orElse(null);

        if (event != null && team1 != null && team2 != null) {
            return new Fixture(
//                    event.getOrganization(), // Assuming organization is derived from event
                    event,
                    team1,
                    team2,
                    fixtureDTO.getDateTime(),
                    fixtureDTO.getGender()
            );
        } else {
            throw new RuntimeException("Failed to create fixture. Invalid event or team information.");
        }
    }
}
