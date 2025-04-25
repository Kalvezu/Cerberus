package de.cerberus.backend.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "logs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_id;

    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users execBy;

    @ManyToOne
    @JoinColumn(name = "action_type_id", nullable = false)
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "action_entity_id", nullable = false)
    private ActionEntity actionEntity;

    @ManyToOne
    @JoinColumn(name = "pc_id")
    private Pc pc;
}