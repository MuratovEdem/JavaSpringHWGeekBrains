package org.example.javaspringhwgeekbrains.seminar5.service;


import lombok.RequiredArgsConstructor;
import org.example.javaspringhwgeekbrains.seminar5.model.Project;
import org.example.javaspringhwgeekbrains.seminar5.page.ProjectPageDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.findById(id) // Optional<Timesheet>
                .map(this::convert);
    }

    private ProjectPageDto convert(Project project) {
        ProjectPageDto projectPageDto = new ProjectPageDto();

        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());

        return projectPageDto;
    }
}
