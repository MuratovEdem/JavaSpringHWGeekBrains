package org.example.javaspringhwgeekbrains.seminar5.service;

import org.example.javaspringhwgeekbrains.seminar5.aspect.Recover;
import org.example.javaspringhwgeekbrains.seminar5.aspect.Timer;
import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar5.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.TimesheetRepository;
import org.slf4j.event.Level;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service // то же самое, что и Component
@Timer(level = Level.TRACE)
public class TimesheetService {

  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;

  public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
    this.timesheetRepository = repository;
    this.projectRepository = projectRepository;
  }

  @Recover
  public Optional<Timesheet> findById(Long id) {
    return timesheetRepository.findById(id);
  }

  public List<Timesheet> findAll() {
    return findAll(null, null);
  }

  public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
    if (Objects.isNull(createdAtBefore) && Objects.isNull(createdAtAfter)) {
      return timesheetRepository.findAll();
    }
    return timesheetRepository.findByCreatedAtBetween(createdAtBefore, createdAtAfter);
  }

  public Timesheet create(Timesheet timesheet) {
    if (Objects.isNull(timesheet.getProjectId())) {
      throw new IllegalArgumentException("projectId must not be null");
    }

    if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
      throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
    }

    timesheet.setCreatedAt(LocalDate.now());
    return timesheetRepository.save(timesheet);
  }

  public void delete(Long id) {
    timesheetRepository.deleteById(id);
  }
}
