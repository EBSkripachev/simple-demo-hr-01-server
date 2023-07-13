package com.example.demo_hr_01.service;

import com.example.demo_hr_01.entity.ProjectEntity;
import com.example.demo_hr_01.exception.ProjectAlreadyExistException;
import com.example.demo_hr_01.exception.ProjectNotFoundException;
import com.example.demo_hr_01.model.Project;
import com.example.demo_hr_01.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public Project create(ProjectEntity project) throws ProjectAlreadyExistException {
        if (projectRepo.findByTitle(project.getTitle()) != null) {
            throw new ProjectAlreadyExistException("Проект уже существует");
        }
        return Project.toModel(projectRepo.save(project));
    }

    public List<Project> getAll() {
        List<ProjectEntity> projects = (List<ProjectEntity>) projectRepo.findAll();
        return projects.stream().map(project -> Project.toModel(project)).collect(Collectors.toList());
    }

    public Long delete(Long id) throws ProjectNotFoundException {
        if (projectRepo.findById(id).orElse(null) == null) {
            throw new ProjectNotFoundException("Проект не найден");
        }
        projectRepo.deleteById(id);
        return id;
    }
}
