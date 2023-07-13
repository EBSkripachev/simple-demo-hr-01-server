package com.example.demo_hr_01.repository;

import com.example.demo_hr_01.entity.TimeSheetEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.Month;
import java.time.Year;


public interface TimeSheetRepo extends CrudRepository<TimeSheetEntity, Long> {
    TimeSheetEntity findByUserIdAndYearAndMonth(Long userId, Year year, Month month);
}
