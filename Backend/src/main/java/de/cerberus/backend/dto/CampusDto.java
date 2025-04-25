package de.cerberus.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CampusDto {
    private Long campusId;
    private String campusName;
    private List<PcPoolDto> pcPools = new ArrayList<>();
}