package com.example.demo_hr_01.repository;

import com.example.demo_hr_01.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
    List<TaskEntity> findByTimeSheetId(Long timeSheetId);
}
