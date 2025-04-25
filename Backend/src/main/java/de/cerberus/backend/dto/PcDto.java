package de.cerberus.backend.dto;

import lombok.Data;

@Data
public class PcDto {
    private Integer pcId;
    private String hostname;
    private String ipaddress;
    private String macaddress;
    private String status;
    private Boolean busy;

}