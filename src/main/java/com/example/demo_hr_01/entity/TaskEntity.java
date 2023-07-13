package com.example.demo_hr_01.entity;

import com.example.demo_hr_01.model.Task;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_sheet_id", nullable = false, updatable = false)
    private TimeSheetEntity timeSheet;

    @ManyToOne
    @JoinColumn(name = "project_id" , nullable = false, updatable = false)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "project_element_id", nullable = true, updatable = false)
    private ProjectElementEntity projectElement;
    private String title;

    private String description;

    @Column(name = "date", updatable = false)
    private Date date;
    private Double hours;

    public TaskEntity() {
    }

    public static TaskEntity toEntity(Task model) {
        TaskEntity entity = new TaskEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setDate(model.getDate());
        entity.setHours(model.getHours());

        TimeSheetEntity timeSheet = new TimeSheetEntity();
        timeSheet.setId(model.getTimeSheetId());
        entity.setTimeSheet(timeSheet);

        ProjectEntity project = new ProjectEntity();
        project.setId(model.getProjectId());
        entity.setProject(project);

        ProjectElementEntity projectElement = new ProjectElementEntity();
        projectElement.setId(model.getProjectElementId());
        entity.setProjectElement(projectElement);

        return entity;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeSheetEntity getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(TimeSheetEntity timeSheet) {
        this.timeSheet = timeSheet;
    }

    public ProjectElementEntity getProjectElement() {
        return projectElement;
    }

    public void setProjectElement(ProjectElementEntity projectElement) {
        this.projectElement = projectElement;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
