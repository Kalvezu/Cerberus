package de.cerberus.backend.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.cerberus.backend.dto.CampusDto;
import de.cerberus.backend.dto.PcPoolDto;
import de.cerberus.backend.entity.Campus;
import de.cerberus.backend.entity.PcPools;
import de.cerberus.backend.repository.CampusRepository;
import de.cerberus.backend.repository.PcPoolRepository;

@Service
public class CampusService {
    private final CampusRepository campusRepo;
    private final PcPoolRepository pcPoolRepo;

    public CampusService(CampusRepository campusRepo,
                         PcPoolRepository pcPoolRepo) {
        this.campusRepo = campusRepo;
        this.pcPoolRepo = pcPoolRepo;
    }

    public List<CampusDto> findAll() {
        return campusRepo.findAll().stream()
            .map(c -> {
                CampusDto d = new CampusDto();
                d.setCampusId(c.getCampusId());
                d.setCampusName(c.getCampusName());
                return d;
            })
            .collect(Collectors.toList());
    }

    public List<CampusDto> findAllWithPools() {
        List<PcPools> pools = pcPoolRepo.findAll();
        Map<Long, CampusDto> map = new LinkedHashMap<>();

        for (PcPools pool : pools) {
            Campus campus = pool.getCampus();

            CampusDto cdto = map.computeIfAbsent(
                    campus.getCampusId(),
                    id -> {
                        CampusDto cDto = new CampusDto();
                        cDto.setCampusId(campus.getCampusId());
                        cDto.setCampusName(campus.getCampusName());
                        return cDto;
                    });

            PcPoolDto ppdto = new PcPoolDto();
            ppdto.setPcPoolId(pool.getPcPoolId());
            ppdto.setPcPoolName(pool.getPcPoolName());

            cdto.getPcPools().add(ppdto);
        }

        return new ArrayList<>(map.values());
    }
}