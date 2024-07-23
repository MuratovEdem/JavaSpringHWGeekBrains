package org.example.javaspringhwgeekbrains.seminar5.repository;


import org.example.javaspringhwgeekbrains.seminar5.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {
}
