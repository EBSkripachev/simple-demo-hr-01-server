package com.example.demo_hr_01.repository;

import com.example.demo_hr_01.entity.ProjectElementEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectElementRepo extends CrudRepository<ProjectElementEntity, Long> {
    ProjectElementEntity findByProjectIdAndTitle(Long id, String title);
}
