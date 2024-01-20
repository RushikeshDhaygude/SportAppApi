package com.sportapi.services;

import com.sportapi.model.DTO.PoolDTO;
import com.sportapi.model.Pool;
import com.sportapi.model.Teams;

import java.util.List;

public interface PoolService {
    Pool createPool(PoolRequest poolRequest);
    List<Pool> getAllPools();

    Pool createPool(PoolDTO poolDTO);

    Pool getPoolById(Long poolId);
    List<Teams> getTeamsForPool(Long poolId);

    Pool updatePool(Long poolId, PoolDTO poolDTO);

    void deletePool(Long poolId);
}
