package com.example.demo_hr_01.model;

import com.example.demo_hr_01.entity.TaskEntity;

import java.util.Date;

public class Task {

    private Long id;
    private Long timeSheetId;
    private Long projectId;
    private Long projectElementId;
    private String title;
    private String description;
    private Date date;
    private Double hours;

    public Task() {
    }

    public static Task toModel(TaskEntity entity) {
        Task model = new Task();
        model.setId(entity.getId());
        model.setTimeSheetId(entity.getTimeSheet().getId());
        model.setProjectElementId(entity.getProjectElement().getId());

        model.setProjectId(entity.getProject().getId());
        if (entity.getProjectElement().getId() != null && entity.getProjectElement().getTitle() != null) {
            model.setTitle(entity.getProjectElement().getTitle());
        } else {
            model.setTitle(entity.getTitle());
        }

        model.setDescription(entity.getDescription());
        model.setDate(entity.getDate());
        model.setHours(entity.getHours());
        return model;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeSheetId() {
        return timeSheetId;
    }

    public void setTimeSheetId(Long timeSheetId) {
        this.timeSheetId = timeSheetId;
    }

    public Long getProjectElementId() {
        return projectElementId;
    }

    public void setProjectElementId(Long projectElementId) {
        this.projectElementId = projectElementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }
}
