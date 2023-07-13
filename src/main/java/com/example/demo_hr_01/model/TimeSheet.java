package com.example.demo_hr_01.model;

import com.example.demo_hr_01.entity.TimeSheetEntity;

import java.time.Month;
import java.time.Year;

public class TimeSheet {
    private Long id;
    private Year year;
    private Month month;
    private Long userId;

    public static TimeSheet toModel(TimeSheetEntity entity) {
        TimeSheet model = new TimeSheet();
        model.setId(entity.getId());
        model.setYear(entity.getYear());
        model.setMonth(entity.getMonth());
        model.setUserId(entity.getUser().getId());
        return model;
    }

    public TimeSheet() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
