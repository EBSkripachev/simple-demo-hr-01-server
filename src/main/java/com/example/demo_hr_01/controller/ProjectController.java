package com.example.demo_hr_01.controller;

import com.example.demo_hr_01.entity.ProjectEntity;
import com.example.demo_hr_01.exception.ProjectAlreadyExistException;
import com.example.demo_hr_01.exception.ProjectNotFoundException;
import com.example.demo_hr_01.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity getAllProjects() {
        try {
            return ResponseEntity.ok(projectService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody ProjectEntity project) {
        try {
            return ResponseEntity.ok(projectService.create(project));
        } catch (ProjectAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.delete(id));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
