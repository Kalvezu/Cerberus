package de.cerberus.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cerberus.backend.dto.CampusDto;
import de.cerberus.backend.service.CampusService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {
    private final CampusService campusService;

    public HomeController(CampusService campusService) {
        this.campusService = campusService;
    }

    
    /** 
     * @return List<CampusDto>
     */
    @GetMapping("/campusWithPools")
    public List<CampusDto> getCampusWithPools() {
        return campusService.findAllWithPools();
    }
}
