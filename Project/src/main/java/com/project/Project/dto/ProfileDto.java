package com.project.Project.dto;

public class ProfileDto {

    private String name;
    private String username;
    private String role;

    public ProfileDto(String name, String username, String role) {
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
