package de.cerberus.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cerberus.backend.dto.CampusDto;
import de.cerberus.backend.dto.PcDto;
import de.cerberus.backend.dto.PcPoolDto;
import de.cerberus.backend.service.CampusService;
import de.cerberus.backend.service.PcPoolService;
import de.cerberus.backend.service.PcService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DevToolsController {

    private final CampusService campusService;
    private final PcPoolService pcPoolService;
    private final PcService pcService;

    public DevToolsController(CampusService campusService, PcPoolService pcPoolService,PcService pcService) {
        this.campusService = campusService;
        this.pcPoolService = pcPoolService;
        this.pcService = pcService;
    }

    
    /** 
     * @return List<CampusDto>
     */
    @GetMapping("/campuses")
    public List<CampusDto> getCampuses() {
        return campusService.findAll();
    }

    @GetMapping("/campuses/{campusId}/pools")
    public List<PcPoolDto> getPools(@PathVariable Integer campusId) {
        return pcPoolService.findByCampus(campusId);
    }

    @GetMapping("/pools/{poolId}/pcs")
    public List<PcDto> getPcs(@PathVariable Integer poolId) {
        return pcService.findByPool(poolId);
    }
}