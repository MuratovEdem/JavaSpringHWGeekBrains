package org.example.javaspringhwgeekbrains.seminar5.repository;

import org.example.javaspringhwgeekbrains.seminar5.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
