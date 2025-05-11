package de.cerberus.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.cerberus.backend.dto.PcDto;
import de.cerberus.backend.entity.Pc;
import de.cerberus.backend.repository.PcRepository;

@Service
public class PcService {
    private final PcRepository pcRepo;

    public PcService(PcRepository pcRepo 
                    ) {
        this.pcRepo = pcRepo;
    }
    
    /** 
     * @param poolId
     * @return List<PcDto>
     */
    public List<PcDto> findByPool(Integer poolId) {
        return pcRepo.findByPcPoolPcPoolId(poolId).stream()
                .map(this::toDto)
                .toList();
    }

    public void runCommand(Integer pcId, String rawCmd) {
        // Befehle einzeln PCs
    }

    private PcDto toDto(Pc p) {
        PcDto d = new PcDto();
        d.setPcId(p.getPcId());
        d.setHostname(p.getHostname());
        d.setIpaddress(p.getIpaddress());
        d.setMacaddress(p.getMacaddress());
        d.setStatus(p.getStatus());
        d.setBusy(p.getBusy());
        return d;
    }
}