package com.example.demo_hr_01.entity;

import com.example.demo_hr_01.model.TimeSheet;
import com.example.demo_hr_01.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Entity
public class TimeSheetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Year year;
    private Month month;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "timeSheet")
    private List<TaskEntity> tasks;

    public TimeSheetEntity() {
    }

    public static TimeSheetEntity toEntity(TimeSheet model) {
        TimeSheetEntity entity = new TimeSheetEntity();
        entity.setId(model.getId());
        entity.setMonth(model.getMonth());
        entity.setYear(model.getYear());
        UserEntity user = new UserEntity();
        user.setId(model.getUserId());
        entity.setUser(user);
        return entity;
    }
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
