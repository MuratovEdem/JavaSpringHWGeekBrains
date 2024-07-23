package org.example.javaspringhwgeekbrains.seminar5.service;

import lombok.RequiredArgsConstructor;
import org.example.javaspringhwgeekbrains.seminar5.model.Employee;
import org.example.javaspringhwgeekbrains.seminar5.model.Project;
import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar5.repository.EmployeeRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.TimesheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Timesheet> getTimesheetsByEmployeeId(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Employee with id = " + id + " does not exists");
        }
        return timesheetRepository.getTimesheetsByEmployeeId(id);
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
