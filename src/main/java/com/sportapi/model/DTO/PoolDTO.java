package com.sportapi.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PoolDTO {
    private String poolName;
    private Long eventId;
    private List<Long> teamIds;

}
