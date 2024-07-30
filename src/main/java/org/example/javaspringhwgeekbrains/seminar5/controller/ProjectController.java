package org.example.javaspringhwgeekbrains.seminar5.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.javaspringhwgeekbrains.seminar5.model.Project;
import org.example.javaspringhwgeekbrains.seminar5.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar5.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(summary = "Get Project", description = "Получить проект по id")
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Timesheets by id", description = "Получить все таймшиты по идентификатору проекта")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        try {
            return ResponseEntity.ok(service.getTimesheets(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all Projects", description = "Получить все проекты")
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Create Project", description = "Создать проект")
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Parameter(description = "Проект") Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    @Operation(summary = "Delete Project by id", description = "Удалить проект по идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
