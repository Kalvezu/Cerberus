package de.cerberus.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.cerberus.backend.entity.Users;
import de.cerberus.backend.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    
    /** 
     * @param email
     * @return Optional<Users>
     */
    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
