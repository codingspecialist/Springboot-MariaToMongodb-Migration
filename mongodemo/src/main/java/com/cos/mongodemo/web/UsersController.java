package com.cos.mongodemo.web;

import java.util.List;

import com.cos.mongodemo.domain.Users;
import com.cos.mongodemo.domain.UsersRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @PostMapping("/users")
    public Users save(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public Users findById(@PathVariable String id) {
        return usersRepository.findById(id).get();
    }

    @PutMapping("/users/{id}")
    public Users update(@PathVariable String id, @RequestBody Users user) {
        Users userDocument = usersRepository.findById(id).get(); // 영속화
        userDocument.setUsername(user.getUsername());
        userDocument.setPassword(user.getPassword());
        return usersRepository.save(userDocument);
    }

    @DeleteMapping("/users/{id}")
    public int deleteById(@PathVariable String id) {
        usersRepository.deleteById(id);
        return 1;
    }
}
