package de.cerberus.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.cerberus.backend.entity.PcPools;

public interface PcPoolRepository extends JpaRepository<PcPools, Integer> {
    @Query("select p from PcPools p where p.campus.campusId = :campusId")
    List<PcPools> findByCampusId(@Param("campusId") Integer campusId);

    List<PcPools> findByCampusCampusId(Integer campusId);


}