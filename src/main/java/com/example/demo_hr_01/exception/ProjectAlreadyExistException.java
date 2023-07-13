package com.example.demo_hr_01.exception;

public class ProjectAlreadyExistException extends Exception{
    public ProjectAlreadyExistException(String message) {
        super(message);
    }
}
