package com.example.demo_hr_01.model;

import com.example.demo_hr_01.entity.ProjectElementEntity;

public class ProjectElement {
    private Long id;
    private String title;
    private Long projectId;

    public static ProjectElement toModel(ProjectElementEntity entity) {
        ProjectElement model = new ProjectElement();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setProjectId(entity.getProject().getId());
        return model;
    }
    public ProjectElement() {
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
