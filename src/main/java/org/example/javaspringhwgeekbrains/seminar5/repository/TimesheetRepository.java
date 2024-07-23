package org.example.javaspringhwgeekbrains.seminar5.repository;

import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

  List<Timesheet> findByCreatedAtBetween(LocalDate createdAtBefore, LocalDate createdAtAfter);

  @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
  List<Timesheet> findByProjectId(Long projectId);

  List<Timesheet> getTimesheetsByEmployeeId(Long id);
}
