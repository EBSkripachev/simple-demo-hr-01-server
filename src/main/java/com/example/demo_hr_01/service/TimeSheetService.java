package com.example.demo_hr_01.service;

import com.example.demo_hr_01.entity.TimeSheetEntity;
import com.example.demo_hr_01.exception.TimeSheetAlreadyExist;
import com.example.demo_hr_01.exception.TimeSheetNotFoundException;
import com.example.demo_hr_01.model.TimeSheet;
import com.example.demo_hr_01.model.User;
import com.example.demo_hr_01.repository.TimeSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSheetService {

    @Autowired
    private TimeSheetRepo timeSheetRepo;

    public List<TimeSheet> getAll() {
        List<TimeSheetEntity> timeSheets = (List<TimeSheetEntity>) timeSheetRepo.findAll();
        return timeSheets.stream().map(timeSheet -> TimeSheet.toModel(timeSheet)).collect(Collectors.toList());
    }

    public TimeSheet create(TimeSheet timeSheet) throws TimeSheetAlreadyExist {
        if (timeSheetRepo.findByUserIdAndYearAndMonth(timeSheet.getUserId(), timeSheet.getYear(), timeSheet.getMonth()) != null) {
            throw new TimeSheetAlreadyExist("Таймшит с таким периодом уже существует");
        }
        return TimeSheet.toModel(timeSheetRepo.save(TimeSheetEntity.toEntity(timeSheet)));
    }

    public Long delete(Long id) throws TimeSheetNotFoundException {
        if (timeSheetRepo.findById(id).orElse(null) == null) {
            throw new TimeSheetNotFoundException("Таймшит не найден");
        }
        timeSheetRepo.deleteById(id);
        return id;
    }

    public TimeSheet getById(Long id) throws TimeSheetNotFoundException {
        if (timeSheetRepo.findById(id).orElse(null) == null) {
            throw new TimeSheetNotFoundException("Таймшит не найден");
        }

        return TimeSheet.toModel(timeSheetRepo.findById(id).get());
    }

}
