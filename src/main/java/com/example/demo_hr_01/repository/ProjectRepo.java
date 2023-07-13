package com.example.demo_hr_01.repository;

import com.example.demo_hr_01.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findByTitle(String title);
}
