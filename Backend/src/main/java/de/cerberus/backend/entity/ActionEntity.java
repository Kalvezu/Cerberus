package de.cerberus.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "action_entity")
public class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_entity_id")
    private Integer actionEntityId;

    @Column(name = "action_entity_name")
    private String actionEntityName;
}