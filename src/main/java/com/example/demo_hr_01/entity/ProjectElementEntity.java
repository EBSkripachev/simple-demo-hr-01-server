package com.example.demo_hr_01.entity;

import com.example.demo_hr_01.model.ProjectElement;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProjectElementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "projectElement")
    private List<TaskEntity> tasks;

    public ProjectElementEntity() {
    }

    public static ProjectElementEntity toEntity(ProjectElement model) {
        ProjectElementEntity entity = new ProjectElementEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setProject(new ProjectEntity());
        entity.getProject().setId(model.getProjectId());
        return entity;
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

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
