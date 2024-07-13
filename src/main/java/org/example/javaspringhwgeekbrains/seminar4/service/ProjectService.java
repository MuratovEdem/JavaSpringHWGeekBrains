package org.example.javaspringhwgeekbrains.seminar4.service;


import org.example.javaspringhwgeekbrains.seminar4.model.Project;
import org.example.javaspringhwgeekbrains.seminar4.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar4.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar4.repository.TimesheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project create(Project project) {
        return projectRepository.create(project);
    }

    public void delete(Long id) {
        projectRepository.delete(id);
    }

    public List<Timesheet> getTimesheets(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Project with id = " + id + " does not exists");
        }

        return timesheetRepository.findByProjectId(id);
    }
}
