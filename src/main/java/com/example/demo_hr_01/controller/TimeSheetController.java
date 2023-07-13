package com.example.demo_hr_01.controller;

import com.example.demo_hr_01.exception.TimeSheetAlreadyExist;
import com.example.demo_hr_01.exception.TimeSheetNotFoundException;
import com.example.demo_hr_01.model.TimeSheet;
import com.example.demo_hr_01.service.TimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/timeSheets")
@CrossOrigin
public class TimeSheetController {

    @Autowired
    private TimeSheetService timeSheetService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(timeSheetService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createTimeSheet(@RequestBody TimeSheet timeSheet) {
        try {
            return ResponseEntity.ok(timeSheetService.create(timeSheet));
        } catch (TimeSheetAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity deleteTimeSheet(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(timeSheetService.delete(id));
        } catch (TimeSheetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getTimeSheetById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(timeSheetService.getById(id));
        } catch (TimeSheetNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
