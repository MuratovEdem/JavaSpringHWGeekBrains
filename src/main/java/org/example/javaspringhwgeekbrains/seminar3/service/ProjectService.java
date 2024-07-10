package org.example.javaspringhwgeekbrains.seminar3.service;


import org.example.javaspringhwgeekbrains.seminar3.model.Project;
import org.example.javaspringhwgeekbrains.seminar3.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar3.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Optional<Project> getById(Long id) {
        return repository.getById(id);
    }

    public List<Project> getAll() {
        return repository.getAll();
    }

    public Project create(Project project) {
        return repository.create(project);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
