package org.example.javaspringhwgeekbrains.seminar3.service;

import org.example.javaspringhwgeekbrains.seminar3.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar3.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar3.repository.TimesheetRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service // то же самое, что и Component
public class TimesheetService {

  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;

  public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
    this.timesheetRepository = repository;
    this.projectRepository = projectRepository;
  }

  public Optional<Timesheet> getById(Long id) {
    return timesheetRepository.getById(id);
  }

  public List<Timesheet> getAll() {
    return timesheetRepository.getAll();
  }

  public Timesheet create(Timesheet timesheet) {
    if (projectRepository.getById(timesheet.getProjectId()).isEmpty()) {
      throw new NoSuchElementException("Проект с идентификатором " + timesheet.getProjectId() + " не найден");
    }

    return timesheetRepository.create(timesheet);
  }

  public void delete(Long id) {
    timesheetRepository.delete(id);
  }

  public List<Timesheet> getTimesheetsByProjectId(Long id) {
    return timesheetRepository.getTimesheetsByProjectId(id);
  }

}
