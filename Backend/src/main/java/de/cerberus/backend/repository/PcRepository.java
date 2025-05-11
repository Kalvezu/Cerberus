package de.cerberus.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cerberus.backend.entity.Pc;

public interface PcRepository extends JpaRepository<Pc, Integer> {
    List<Pc> findByPcPoolPcPoolId(Integer poolId);
}