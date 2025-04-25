package de.cerberus.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.cerberus.backend.dto.PcDto;
import de.cerberus.backend.repository.PcRepository;

@Service
public class PcService {
    private final PcRepository pcRepo;

    public PcService(PcRepository pcRepo) {
        this.pcRepo = pcRepo;
    }

    public List<PcDto> findByPool(Integer poolId) {
        return pcRepo.findByPcPoolPcPoolId(poolId).stream()
            .map(p -> {
                PcDto d = new PcDto();
                d.setPcId(p.getPcId());
                d.setHostname(p.getHostname());
                d.setIpaddress(p.getIpaddress());
                d.setMacaddress(p.getMacaddress());
                d.setStatus(p.getStatus());
                d.setBusy(p.getBusy());
                return d;
            })
            .collect(Collectors.toList());
    }
}