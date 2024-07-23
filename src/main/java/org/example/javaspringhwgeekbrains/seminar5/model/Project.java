package org.example.javaspringhwgeekbrains.seminar5.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;


//    @ManyToMany(mappedBy = "projects")
//    private Set<Employee> employees = new HashSet<>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//
//        if (!(o instanceof Project)) return false;
//
//        return id != null && id.equals(((Project) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
}

