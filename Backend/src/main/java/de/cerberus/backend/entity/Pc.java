package de.cerberus.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pc")
public class Pc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_id")
    private Integer pcId;

    @Column(nullable = false)
    private String hostname;

    @Column(nullable = false)
    private String ipaddress;

    @Column(nullable = false)
    private String macaddress;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private Boolean busy;

    @ManyToOne
    @JoinColumn(name = "pc_pool_id", nullable = false)
    private PcPools pcPool;
}