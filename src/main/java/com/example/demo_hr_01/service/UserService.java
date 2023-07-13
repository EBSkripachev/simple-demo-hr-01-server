package com.example.demo_hr_01.service;

import com.example.demo_hr_01.entity.UserEntity;
import com.example.demo_hr_01.exception.UserAlreadyExistException;
import com.example.demo_hr_01.exception.UserNotFoundException;
import com.example.demo_hr_01.model.User;
import com.example.demo_hr_01.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User create(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return User.toModel(userRepo.save(user));
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public List<User> getAll() {
        List<UserEntity> entity = (List<UserEntity>) userRepo.findAll();
        return entity.stream().map(user -> User.toModel(user)).collect(Collectors.toList());
    }

    public Long delete(Long id) throws UserNotFoundException{
        if (userRepo.findById(id).orElse(null) == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        userRepo.deleteById(id);
        return id;
    }

}
