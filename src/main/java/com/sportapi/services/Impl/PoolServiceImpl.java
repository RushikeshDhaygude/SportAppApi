package com.sportapi.services.Impl;

import com.sportapi.model.DTO.PoolDTO;
import com.sportapi.model.Event;
import com.sportapi.model.Pool;
import com.sportapi.model.Teams;
import com.sportapi.repositories.PoolRepository;
import com.sportapi.services.EventService;
import com.sportapi.services.PoolRequest;
import com.sportapi.services.PoolService;
import com.sportapi.services.TeamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PoolServiceImpl.class);

    @Override
    @Transactional
    public Pool createPool(PoolDTO poolDTO) {
        try {
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
        } catch (Exception e) {
            logger.error("Error creating pool", e);
            throw new RuntimeException("Error creating pool", e); // or handle it as appropriate
        }
    }

    @Override
    public Pool createPool(PoolRequest poolRequest) {
        return null;
    }

    @Override
    public List<Pool> getAllPools() {
        return poolRepository.findAll();
    }

    @Override
    public Pool getPoolById(Long poolId) {
        return poolRepository.findById(poolId).orElse(null);
    }

    @Override
    public List<Teams> getTeamsForPool(Long poolId) {
        Pool pool = poolRepository.findById(poolId).orElse(null);
        if (pool != null) {
            return pool.getTeams();
        } else {
            return null;  // Handle pool not found
        }
    }

    @Override
    @Transactional
    public Pool updatePool(Long poolId, PoolDTO poolDTO) {
        try {
            Pool existingPool = poolRepository.findById(poolId).orElse(null);
            if (existingPool == null) {
                return null;  // Handle pool not found
            }

            // Update pool properties
            existingPool.setPoolName(poolDTO.getPoolName());

            // Assuming that you can also update the associated event
            Event event = eventService.getEventById(poolDTO.getEventId());
            if (event != null) {
                existingPool.setEvent(event);
            }

            // Update the teams associated with the pool
            List<Teams> teams = poolDTO.getTeamIds().stream()
                    .map(teamId -> teamService.getTeamById(teamId))
                    .filter(team -> team != null)
                    .collect(Collectors.toList());

            existingPool.setTeams(teams);

            return poolRepository.save(existingPool);
        } catch (Exception e) {
            logger.error("Error updating pool", e);
            throw new RuntimeException("Error updating pool", e); // or handle it as appropriate
        }
    }

    @Override
    @Transactional
    public void deletePool(Long poolId) {
        try {
            poolRepository.deleteById(poolId);
        } catch (Exception e) {
            logger.error("Error deleting pool", e);
            throw new RuntimeException("Error deleting pool", e); // or handle it as appropriate
        }
    }

    // Other methods as needed

//    @Override
//    @Transactional
//    public void addTeamToPool(Long poolId, Long teamId) {
//        Pool pool = poolRepository.findById(poolId).orElse(null);
//        Teams team = teamService.getTeamById(teamId);
//
//        if (pool != null && team != null) {
//            pool.getTeams().add(team);
//            poolRepository.save(pool);
//        }
//    }

//    @Override
//    @Transactional
//    public void removeTeamFromPool(Long poolId, Long teamId) {
//        Pool pool = poolRepository.findById(poolId).orElse(null);
//
//        if (pool != null) {
//            pool.getTeams().removeIf(team -> team.getId().equals(teamId));
//            poolRepository.save(pool);
//        }
//    }
}
