package com.sportapi.controllers;

import com.sportapi.model.DTO.PoolDTO;
import com.sportapi.model.Pool;
import com.sportapi.model.Teams;
import com.sportapi.services.PoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PoolController.class);

    @PostMapping
    public ResponseEntity<Pool> createPool(@RequestBody PoolDTO poolDTO) {
        try {
            Pool createdPool = poolService.createPool(poolDTO);
            return new ResponseEntity<>(createdPool, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating pool", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pool>> getAllPools() {
        try {
            List<Pool> pools = poolService.getAllPools();
            return new ResponseEntity<>(pools, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all pools", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{poolId}")
    public ResponseEntity<Pool> getPoolById(@PathVariable Long poolId) {
        try {
            Pool pool = poolService.getPoolById(poolId);
            if (pool != null) {
                return new ResponseEntity<>(pool, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error fetching pool by ID: " + poolId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{poolId}/teams")
    public ResponseEntity<List<Teams>> getTeamsForPool(@PathVariable Long poolId) {
        try {
            List<Teams> teams = poolService.getTeamsForPool(poolId);
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching teams for pool with ID: " + poolId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{poolId}")
    public ResponseEntity<Pool> updatePool(@PathVariable Long poolId, @RequestBody PoolDTO poolDTO) {
        try {
            Pool updatedPool = poolService.updatePool(poolId, poolDTO);
            if (updatedPool != null) {
                return new ResponseEntity<>(updatedPool, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error updating pool with ID: " + poolId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{poolId}")
    public ResponseEntity<Void> deletePool(@PathVariable Long poolId) {
        try {
            poolService.deletePool(poolId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting pool with ID: " + poolId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Other methods as needed
}
