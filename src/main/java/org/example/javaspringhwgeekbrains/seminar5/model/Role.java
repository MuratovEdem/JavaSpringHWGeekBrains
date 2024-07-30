package org.example.javaspringhwgeekbrains.seminar5.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Users> users;

//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, users);
//    }
}
