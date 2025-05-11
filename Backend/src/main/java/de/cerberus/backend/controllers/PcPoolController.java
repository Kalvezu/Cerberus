package de.cerberus.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cerberus.backend.dto.PcPoolDto;
import de.cerberus.backend.service.PcPoolService;
import de.cerberus.backend.service.PcService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PcPoolController {
    private final PcPoolService pcPoolService;
    private final PcService pcService;

    public PcPoolController(PcPoolService pcPoolService,PcService pcService) {
        this.pcPoolService = pcPoolService;
        this.pcService = pcService;
    }
    
    /** 
     * @param poolId
     * @return PcPoolDto
     */
    @GetMapping("/pools/{poolId}")
    public PcPoolDto getPool(@PathVariable Integer poolId) {
        return pcPoolService.findOne(poolId);
    }

    @PostMapping("/pools/{poolId}/{cmd}")
    public ResponseEntity<Void> runPoolCmd(@PathVariable Integer poolId,
                                           @PathVariable String cmd) {
        pcPoolService.runCommand(poolId, cmd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pcs/{pcId}/{cmd}")
    public ResponseEntity<Void> runPcCmd(@PathVariable Integer pcId,
                                         @PathVariable String cmd) {
        pcService.runCommand(pcId, cmd);
        return ResponseEntity.ok().build();
    }
}
