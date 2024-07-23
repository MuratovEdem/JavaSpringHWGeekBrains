package org.example.javaspringhwgeekbrains.seminar5.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "employee_project",
//            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
//    )
//    private Set<Project> projects;

//    public void addProject(Project project) {
//        this.projects.add(project);
//        project.getEmployees().add(this);
//    }
//
//    public void removeProject(Project project) {
//        this.projects.remove(project);
//        project.getEmployees().remove(this);
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//
//        if (!(o instanceof Employee)) return false;
//
//        return id != null && id.equals(((Employee) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
}
