package org.example.javaspringhwgeekbrains.seminar3.controller;


import org.example.javaspringhwgeekbrains.seminar3.model.Project;
import org.example.javaspringhwgeekbrains.seminar3.model.Timesheet;
import org.example.javaspringhwgeekbrains.seminar3.service.ProjectService;
import org.example.javaspringhwgeekbrains.seminar3.service.TimesheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TimesheetService timesheetService;

    public ProjectController(ProjectService projectService, TimesheetService timesheetService) {
        this.projectService = projectService;
        this.timesheetService = timesheetService;
    }

    @GetMapping("/{id}") // получить все
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> ts = projectService.getById(id);

        if (ts.isPresent()) {
//      return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheetsByProjectId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(timesheetService.getTimesheetsByProjectId(id));
    }

    @PostMapping // создание нового ресурса
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = projectService.create(project);

        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
