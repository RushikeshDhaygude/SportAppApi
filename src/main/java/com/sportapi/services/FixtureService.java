package com.sportapi.services;

import com.sportapi.model.DTO.FixtureDTO;

import java.util.List;

public interface FixtureService {

    List<FixtureDTO> getAllFixtures();

    FixtureDTO getFixtureById(Long id);

    void createFixture(FixtureDTO fixtureDTO);

    void updateFixture(Long id, FixtureDTO fixtureDTO);

    void deleteFixture(Long id);
}
