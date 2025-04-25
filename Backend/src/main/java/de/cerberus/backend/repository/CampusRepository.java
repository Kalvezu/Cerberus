package de.cerberus.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.cerberus.backend.entity.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    @Query("select distinct c from Campus c left join fetch c.pcPools")
    List<Campus> findAllWithPools();
}
