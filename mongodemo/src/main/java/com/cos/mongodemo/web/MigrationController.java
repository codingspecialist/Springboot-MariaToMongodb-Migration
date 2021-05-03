package com.cos.mongodemo.web;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cos.mongodemo.domain.Users;
import com.cos.mongodemo.domain.UsersRepository;
import com.cos.mongodemo.web.dto.UsersList;
import com.cos.mongodemo.web.dto.UsersRespDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MigrationController {

    private final UsersRepository usersRepository;

    @GetMapping("/migration/users")
    public List<Users> usersCollection() {
        RestTemplate rt = new RestTemplate();
        UsersList response = rt.getForObject("http://localhost:8000/users", UsersList.class);
        List<UsersRespDto> usersRespDtos = response.getData();
        System.out.println(usersRespDtos);
        List<Users> users = usersRespDtos.stream().map((u) -> {
            return Users.builder().id(u.getId()).username(u.getUsername()).password(u.getPassword())
                    .boards(u.getBoards()).build();
        }).collect(Collectors.toList());

        return usersRepository.saveAll(users);
    }
}
