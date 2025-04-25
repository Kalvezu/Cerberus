package de.cerberus.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pc_pools")
public class PcPools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pc_pool_id")
    private Integer pcPoolId;

    @Column(nullable = false, name="pc_pool_name")
    private String pcPoolName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    @JsonBackReference 
    private Campus campus;
}
