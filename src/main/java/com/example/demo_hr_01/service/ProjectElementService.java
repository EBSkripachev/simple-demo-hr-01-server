package com.example.demo_hr_01.service;

import com.example.demo_hr_01.entity.ProjectElementEntity;
import com.example.demo_hr_01.exception.ProjectElementAlreadyExistException;
import com.example.demo_hr_01.exception.ProjectElementNotFoundException;
import com.example.demo_hr_01.exception.ProjectNotFoundException;
import com.example.demo_hr_01.model.ProjectElement;
import com.example.demo_hr_01.repository.ProjectElementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectElementService {
    @Autowired
    private ProjectElementRepo projectElementRepo;

    public ProjectElement create(ProjectElement projectElement) throws ProjectElementAlreadyExistException {
        if (projectElementRepo
                .findByProjectIdAndTitle(projectElement.getProjectId(), projectElement.getTitle()) != null) {
            throw new ProjectElementAlreadyExistException("Задача уже существует");
        }
        return ProjectElement.toModel(projectElementRepo.save(ProjectElementEntity.toEntity(projectElement)));
    }

    public List<ProjectElement> getAll() {
        List<ProjectElementEntity> projectElementEntities = (List<ProjectElementEntity>) projectElementRepo.findAll();
        return projectElementEntities.stream()
                .map(element -> ProjectElement.toModel(element)).collect(Collectors.toList());
    }

    public Long delete(Long id) throws ProjectElementNotFoundException {
        if (projectElementRepo.findById(id).orElse(null) == null) {
            throw new ProjectElementNotFoundException("Задача не найден");
        }
        projectElementRepo.deleteById(id);
        return id;
    }

}
