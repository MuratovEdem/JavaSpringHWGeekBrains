package org.example.javaspringhwgeekbrains.seminar4.service;

import org.example.javaspringhwgeekbrains.seminar4.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar4.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar4.repository.TimesheetRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service // то же самое, что и Component
public class TimesheetService {

  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;

  public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
    this.timesheetRepository = repository;
    this.projectRepository = projectRepository;
  }

  public Optional<Timesheet> findById(Long id) {
    return timesheetRepository.findById(id);
  }

  public List<Timesheet> findAll() {
    return findAll(null, null);
  }

  public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
    return timesheetRepository.findAll(createdAtBefore, createdAtAfter);
  }

  public Timesheet create(Timesheet timesheet) {
    if (Objects.isNull(timesheet.getProjectId())) {
      throw new IllegalArgumentException("projectId must not be null");
    }

    if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
      throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
    }

    timesheet.setCreatedAt(LocalDate.now());
    return timesheetRepository.create(timesheet);
  }

  public void delete(Long id) {
    timesheetRepository.delete(id);
  }

}
