package com.example.demo_hr_01.model;

import com.example.demo_hr_01.entity.ProjectEntity;

public class Project {
    private Long id;
    private String title;

    public Project() {
    }

    public static Project toModel(ProjectEntity entity) {
        Project model = new Project();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
