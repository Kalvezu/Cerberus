package de.cerberus.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.cerberus.backend.dto.PcPoolDto;
import de.cerberus.backend.repository.PcPoolRepository;

@Service
public class PcPoolService {
    private final PcPoolRepository pprepo;

    public PcPoolService(PcPoolRepository pprepo) {
        this.pprepo = pprepo;
    }

    public List<PcPoolDto> findByCampus(Long campusId) {
        return pprepo.findByCampusId(campusId).stream()
            .map(p -> {
                PcPoolDto d = new PcPoolDto();
                d.setPcPoolId(p.getPcPoolId());
                d.setPcPoolName(p.getPcPoolName());
                return d;
            })
            .collect(Collectors.toList());
    }
}