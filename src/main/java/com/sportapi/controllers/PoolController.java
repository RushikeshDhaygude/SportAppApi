package com.sportapi.controllers;

import com.sportapi.model.DTO.PoolDTO;
import com.sportapi.model.Pool;
import com.sportapi.model.Teams;
import com.sportapi.services.PoolRequest;
import com.sportapi.services.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pools")
public class PoolController {

    @Autowired
    private PoolService poolService;

    @PostMapping
    public ResponseEntity<Pool> createPool(@RequestBody PoolDTO poolDTO) {
        try {
            Pool createdPool = poolService.createPool(poolDTO);
            return new ResponseEntity<>(createdPool, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pool>> getAllPools() {
        List<Pool> pools = poolService.getAllPools();
        return new ResponseEntity<>(pools, HttpStatus.OK);
    }

    @GetMapping("/{poolId}")
    public ResponseEntity<Pool> getPoolById(@PathVariable Long poolId) {
        Pool pool = poolService.getPoolById(poolId);
        if (pool != null) {
            return new ResponseEntity<>(pool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{poolId}/teams")
    public ResponseEntity<List<Teams>> getTeamsForPool(@PathVariable Long poolId) {
        List<Teams> teams = poolService.getTeamsForPool(poolId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PutMapping("/{poolId}")
    public ResponseEntity<Pool> updatePool(@PathVariable Long poolId, @RequestBody PoolDTO poolDTO) {
        Pool updatedPool = poolService.updatePool(poolId, poolDTO);
        if (updatedPool != null) {
            return new ResponseEntity<>(updatedPool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{poolId}")
    public ResponseEntity<Void> deletePool(@PathVariable Long poolId) {
        poolService.deletePool(poolId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Other methods as needed
}
