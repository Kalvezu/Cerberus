package de.cerberus.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cerberus.backend.entity.Users;
import de.cerberus.backend.service.UsersService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {

    @Autowired
    private UsersService usersService;

    
    /** 
     * @param email
     * @return Optional<Users>
     */
    @GetMapping("/{email}")
    public Optional<Users> getUserByEmail(@PathVariable String email) {
        return usersService.getUserByEmail(email);
    }
}
