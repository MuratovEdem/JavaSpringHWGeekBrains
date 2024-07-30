package org.example.javaspringhwgeekbrains.seminar5;

import org.example.javaspringhwgeekbrains.seminar5.model.*;
import org.example.javaspringhwgeekbrains.seminar5.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);

		Role roleAdmins = new Role();
		roleAdmins.setName(RoleEnum.ADMIN.getName());
		roleRepository.save(roleAdmins);

		Role roleUsers = new Role();
		roleUsers.setName(RoleEnum.USER.getName());
		roleRepository.save(roleUsers);

		Role roleRest = new Role();
		roleRest.setName("rest");
		roleRepository.save(roleRest);

		UserRepository userRepository = ctx.getBean(UserRepository.class);

		Users user = new Users();
		user.setLogin("user"); //user
		user.setPassword("$2a$12$.dlnBAYq6sOUumn3jtG.AepxdSwGxJ8xA2iAPoCHSH61Vjl.JbIfq");
		user.setRoles(List.of(roleUsers));


		userRepository.save(user);

		Users admin = new Users();
		admin.setLogin("admin"); // admin
		admin.setPassword("$2a$12$QPoOV5Qr8sRD5lkcugid8ezyLwZmcFt2j0kh6XLsPf/IgkmLxe5SK");
		admin.setRoles(List.of(roleAdmins, roleUsers));

		userRepository.save(admin);


		Users rest = new Users();
		rest.setLogin("rest"); // rest
		rest.setPassword("$2a$12$tZaB13uZJJkD9zgpSiwZXeeYtOY/vjDR9XQwm2woqGHpmxhQXeTYq");
		rest.setRoles(List.of(roleRest));


		userRepository.save(rest);


		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);
		for (int i = 1; i <= 3; i++) {
			Employee employee = new Employee();
			employee.setName("Employee #" + i);
			employeeRepo.save(employee);
		}

		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepo.save(project);
		}

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);

		LocalDate createdAt = LocalDate.now();
		for (int i = 1; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 4));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}


	}
}
