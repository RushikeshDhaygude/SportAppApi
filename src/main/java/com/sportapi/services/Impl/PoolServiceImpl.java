package com.sportapi.services.Impl;

import com.sportapi.model.DTO.PoolDTO;
import com.sportapi.model.Event;
import com.sportapi.model.Pool;
import com.sportapi.model.Teams;
import com.sportapi.repositories.PoolRepository;
import com.sportapi.services.EventService;
import com.sportapi.services.PoolService;
import com.sportapi.services.TeamsService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolRepository poolRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private TeamsService teamService;

    @Override
    @Transactional
    public Pool createPool(PoolDTO poolDTO) {
        Event event = eventService.getEventById(poolDTO.getEventId());
        if (event == null) {
            return null;  // Handle event not found
        }

        List<Teams> teams = poolDTO.getTeamIds().stream()
                .map(teamId -> teamService.getTeamById(teamId))
                .filter(team -> team != null)
                .collect(Collectors.toList());

        if (teams.size() != poolDTO.getTeamIds().size()) {
            return null;  // Handle some teams not found
        }

        Pool pool = new Pool();
        pool.setPoolName(poolDTO.getPoolName());
        pool.setEvent(event);
        pool.setTeams(teams);

        return poolRepository.save(pool);
    }

    @Override
    @Transactional
    public List<Pool> getAllPools() {
        return poolRepository.findAll();
    }

    @Override
    @Transactional
    public Pool getPoolById(Long poolId) {
        Pool pool = poolRepository.findById(poolId).orElse(null);
        if (pool != null) {
            Hibernate.initialize(pool.getTeams()); // Force initialization of the teams collection
        }
        return pool;
    }

    @Override
    @Transactional
    public List<Teams> getTeamsForPool(Long poolId) {
        Pool pool = poolRepository.findById(poolId).orElse(null);
        if (pool != null) {
            Hibernate.initialize(pool.getTeams()); // Force initialization of the teams collection
            return pool.getTeams();
        } else {
            return null;  // Handle pool not found
        }
    }

    @Override
    @Transactional
    public Pool updatePool(Long poolId, PoolDTO poolDTO) {
        Pool existingPool = poolRepository.findById(poolId).orElse(null);
        if (existingPool == null) {
            return null;  // Handle pool not found
        }

        existingPool.setPoolName(poolDTO.getPoolName());

        Event event = eventService.getEventById(poolDTO.getEventId());
        if (event != null) {
            existingPool.setEvent(event);
        }

        List<Teams> teams = poolDTO.getTeamIds().stream()
                .map(teamId -> teamService.getTeamById(teamId))
                .filter(team -> team != null)
                .collect(Collectors.toList());

        existingPool.setTeams(teams);

        return poolRepository.save(existingPool);
    }

    @Override
    @Transactional
    public void deletePool(Long poolId) {
        poolRepository.deleteById(poolId);
    }
}
