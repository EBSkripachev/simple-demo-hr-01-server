package com.example.demo_hr_01.controller;

import com.example.demo_hr_01.exception.*;
import com.example.demo_hr_01.model.Task;
import com.example.demo_hr_01.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.create(task));
        } catch (TaskHoursIsEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (TaskDateIsWrongException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (TimeSheetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ProjectElementNotFoundException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllTasks(@RequestParam(value = "timeSheetId", required = false) Long timeSheetId) {
        try {
            return ResponseEntity.ok(taskService.getAll(timeSheetId));
        } catch (TimeSheetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.update(task));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.delete(id));
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
