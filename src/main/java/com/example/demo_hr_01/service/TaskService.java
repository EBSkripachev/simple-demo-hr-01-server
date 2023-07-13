package com.example.demo_hr_01.service;

import com.example.demo_hr_01.entity.ProjectElementEntity;
import com.example.demo_hr_01.entity.TaskEntity;
import com.example.demo_hr_01.entity.TimeSheetEntity;
import com.example.demo_hr_01.exception.*;
import com.example.demo_hr_01.model.Task;
import com.example.demo_hr_01.repository.ProjectElementRepo;
import com.example.demo_hr_01.repository.ProjectRepo;
import com.example.demo_hr_01.repository.TaskRepo;
import com.example.demo_hr_01.repository.TimeSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TimeSheetRepo timeSheetRepo;

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectElementRepo projectElementRepo;

    public Task create(Task task) throws TimeSheetNotFoundException, ProjectElementNotFoundException,
            TaskDateIsWrongException, TaskHoursIsEmptyException {
        TimeSheetEntity timeSheet = timeSheetRepo.findById(task.getTimeSheetId()).get();

        if (timeSheet == null) {
            throw new TimeSheetNotFoundException("Taймшит не существует");
        }

        validate(task);

        if (task.getProjectId() != null) {
            if (projectRepo.findById(task.getProjectId()).orElse(null) == null) {
                throw new ProjectElementNotFoundException("Проект не существует");
            }
        }

        if (task.getProjectElementId() != null) {
            ProjectElementEntity projectElement = projectElementRepo.findById(task.getProjectElementId()).get();
            if (projectElement == null) {
                throw new ProjectElementNotFoundException("Задача не существует");
            }
            task.setTitle(projectElement.getTitle());
        }

        return Task.toModel(taskRepo.save(TaskEntity.toEntity(task)));

    }

    public List<Task> getAll(Long timeSheetId) throws TimeSheetNotFoundException {
        List<TaskEntity> tasks;
        if (timeSheetId != null) {
            if (timeSheetRepo.findById(timeSheetId).orElse(null) == null) {
                throw new TimeSheetNotFoundException("Taймшит не существует");
            }
            tasks = (List<TaskEntity>) taskRepo.findByTimeSheetId(timeSheetId);
        } else {
            tasks = (List<TaskEntity>) taskRepo.findAll();
        }
        return tasks.stream().map(task -> Task.toModel(task)).collect(Collectors.toList());
    }

    public Task update(Task task) throws TaskNotFoundException, TaskHoursIsEmptyException,
            TaskDateIsWrongException {
        if (taskRepo.findById(task.getId()).orElse(null) == null) {
            throw new TaskNotFoundException("Задача не найдена");
        }
        validate(task);
        taskRepo.save(TaskEntity.toEntity(task));
        return Task.toModel(taskRepo.findById(task.getId()).get());
    }

    public Long delete(Long id) throws TaskNotFoundException {
        if (taskRepo.findById(id).orElse(null) == null) {
            throw new TaskNotFoundException("Задача не найдена");
        }
        taskRepo.deleteById(id);
        return id;
    }


    private void validate(Task task) throws TaskHoursIsEmptyException, TaskDateIsWrongException {

        if (task.getDate() == null) {
            throw new TaskDateIsWrongException("Дата не заполнена");
        }

        TimeSheetEntity timeSheet = timeSheetRepo.findById(task.getTimeSheetId()).get();
        Calendar calendar = Calendar.getInstance();
        calendar.set(timeSheet.getYear().get(ChronoField.YEAR_OF_ERA),
                timeSheet.getMonth().get(ChronoField.MONTH_OF_YEAR) - 1, 1, 0, 0,0);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();


        if (task.getDate() == null || task.getDate().after(endDate) || task.getDate().before(startDate)) {
            throw new TaskDateIsWrongException("Дата не в периоде таймшита");
        }

        if (task.getHours() == null || task.getHours() == 0) {
            throw new TaskHoursIsEmptyException("Время не заполнено");
        }
    }
}
