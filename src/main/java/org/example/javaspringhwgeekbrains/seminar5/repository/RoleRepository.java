package org.example.javaspringhwgeekbrains.seminar5.repository;

import org.example.javaspringhwgeekbrains.seminar5.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
