package de.cerberus.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.cerberus.backend.dto.PcPoolDto;
import de.cerberus.backend.entity.PcPools;
import de.cerberus.backend.repository.PcPoolRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PcPoolService {

    private final PcPoolRepository repo;

    public PcPoolService(PcPoolRepository repo) {
        this.repo = repo;
    }

    
    /** 
     * @param campusId
     * @return List<PcPoolDto>
     */
    public List<PcPoolDto> findByCampus(Integer campusId) {
        return repo.findByCampusCampusId(campusId).stream()
                .map(this::toDto)
                .toList();
    }

    public PcPoolDto findOne(Integer poolId) {
        PcPools entity = repo.findById(poolId)
                .orElseThrow(() -> new EntityNotFoundException("Pool not found"));
        return toDto(entity);
    }

    public void runCommand(Integer poolId, String rawCmd) {
        //Befehle Methoden PcPools
    }

    private PcPoolDto toDto(PcPools p) {
        PcPoolDto dto = new PcPoolDto();
        dto.setPcPoolId(p.getPcPoolId());
        dto.setPcPoolName(p.getPcPoolName());
        return dto;
    }
}