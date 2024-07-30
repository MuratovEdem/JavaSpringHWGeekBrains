package org.example.javaspringhwgeekbrains.seminar5.model;

public enum RoleEnum {

    ADMIN("admin"), USER("user"), REST("rest");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
