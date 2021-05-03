package com.cos.mariademo.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mariademo.domain.Users;
import com.cos.mariademo.domain.UsersRepository;
import com.cos.mariademo.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersRepository usersRepository;

    @GetMapping("/users")
    public CMRespDto<?> findAll() {
        return new CMRespDto<>(1, usersRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public CMRespDto<?> findById(@PathVariable Long id) {
        return new CMRespDto<>(1, usersRepository.findById(id).get());
    }

    @PostMapping("/users")
    public CMRespDto<?> save(@RequestBody Users user) {
        return new CMRespDto<>(1, usersRepository.save(user));
    }

    @PutMapping("/users/{id}")
    public CMRespDto<?> update(@PathVariable Long id, @RequestBody Users user) {
        Users userEntity = usersRepository.findById(id).get();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return new CMRespDto<>(1, usersRepository.save(userEntity));
    }

    @DeleteMapping("/users/{id}")
    public CMRespDto<?> deleteById(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return new CMRespDto<>(1, null);
    }
}
