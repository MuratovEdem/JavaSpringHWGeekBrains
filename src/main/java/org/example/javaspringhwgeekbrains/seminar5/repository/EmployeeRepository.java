package org.example.javaspringhwgeekbrains.seminar5.repository;

import org.example.javaspringhwgeekbrains.seminar5.model.Employee;
import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}