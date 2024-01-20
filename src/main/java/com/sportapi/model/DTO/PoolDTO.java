package com.sportapi.model.DTO;

import java.util.List;

public class PoolDTO {
    private String poolName;
    private Long eventId;
    private List<Long> teamIds;

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }
}
