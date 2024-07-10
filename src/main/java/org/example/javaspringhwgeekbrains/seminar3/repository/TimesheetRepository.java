package org.example.javaspringhwgeekbrains.seminar3.repository;

import org.example.javaspringhwgeekbrains.seminar3.model.Timesheet;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository // @Component для классов, работающих с данными
public class TimesheetRepository {

  private static Long sequence = 1L;
  private final List<Timesheet> timesheets = new ArrayList<>();

  public Optional<Timesheet> getById(Long id) {
    // select * from timesheets where id = $id
    return timesheets.stream()
      .filter(it -> Objects.equals(it.getId(), id))
      .findFirst();
  }

  public List<Timesheet> getAll() {
    return List.copyOf(timesheets);
  }

  public Timesheet create(Timesheet timesheet) {
    timesheet.setId(sequence++);
    timesheet.setCreatedAt(LocalDate.now());
    timesheets.add(timesheet);
    return timesheet;
  }

  public void delete(Long id) {
    timesheets.stream()
      .filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .ifPresent(timesheets::remove); // если нет - иногда посылают 404 Not Found
  }

  public List<Timesheet> getTimesheetsByProjectId(Long id) {
    return timesheets.stream().filter(timesheet -> timesheet.getProjectId().equals(id)).toList();
  }

}
