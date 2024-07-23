package org.example.javaspringhwgeekbrains.seminar5;

import org.example.javaspringhwgeekbrains.seminar5.model.Employee;
import org.example.javaspringhwgeekbrains.seminar5.model.Project;
import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar5.repository.EmployeeRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.ProjectRepository;
import org.example.javaspringhwgeekbrains.seminar5.repository.TimesheetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

//		JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
//		jdbcTemplate.execute("delete from project");

		EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);
		ProjectRepository projectRepository = ctx.getBean(ProjectRepository.class);


		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);

			Employee employee = new Employee();

			employee.setName("Employee " + i);

			employeeRepository.save(employee);
			projectRepository.save(project);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1,6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}


	}
}
