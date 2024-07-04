package org.example.javaspringhwgeekbrains.seminar2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaSpringHwGeekBrainsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JavaSpringHwGeekBrainsApplication.class, args);

		Board board = context.getBean(Board.class);

		for (int i = 0; i < 10; i++) {
			System.out.println(board.newTicket());
		}
	}
}
