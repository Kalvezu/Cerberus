package de.cerberus.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cerberus.backend.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
