package com.sportapi.services;

import com.sportapi.model.DTO.FixtureDTO;
import com.sportapi.model.Fixture;
import com.sportapi.model.Teams;
import jakarta.transaction.Transactional;

import java.util.List;

public interface FixtureService {
    List<Fixture> getAllFixtures();

    Fixture getFixtureById(Long fixtureId);

    Fixture createFixture(FixtureDTO fixtureDTO);

    Fixture updateFixture(Long fixtureId, FixtureDTO fixtureDTO);

    void deleteFixture(Long fixtureId);

    @Transactional
    List<Teams> getTeamsForFixture(Long fixtureId);

    @Transactional
    void addTeamToFixture(Long fixtureId, Long teamId);

    @Transactional
    void removeTeamFromFixture(Long fixtureId, Long teamId);

    // Other methods...
}
