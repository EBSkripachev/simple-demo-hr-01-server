package com.example.demo_hr_01.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<ProjectElementEntity> elements;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<TaskEntity> tasks;

    public List<ProjectElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ProjectElementEntity> elements) {
        this.elements = elements;
    }

    public ProjectEntity() {
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
