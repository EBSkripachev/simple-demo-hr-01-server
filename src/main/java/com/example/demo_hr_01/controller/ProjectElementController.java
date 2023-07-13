package com.example.demo_hr_01.controller;

import com.example.demo_hr_01.exception.ProjectElementAlreadyExistException;
import com.example.demo_hr_01.exception.ProjectElementNotFoundException;
import com.example.demo_hr_01.exception.ProjectNotFoundException;
import com.example.demo_hr_01.model.ProjectElement;
import com.example.demo_hr_01.service.ProjectElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projectElements")
@CrossOrigin
public class ProjectElementController {

    @Autowired
    private ProjectElementService projectElementService;

    @PostMapping
    public ResponseEntity createProjectElement(@RequestBody ProjectElement projectElement) {
        try {
            return ResponseEntity.ok(projectElementService.create(projectElement));
        } catch (ProjectElementAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(projectElementService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProjectElement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectElementService.delete(id));
        } catch (ProjectElementNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
