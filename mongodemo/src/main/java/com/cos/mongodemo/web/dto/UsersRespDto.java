package com.cos.mongodemo.web.dto;

import java.util.List;

import com.cos.mongodemo.domain.Board;

import lombok.Data;

@Data
public class UsersRespDto {
    private Long id;
    private String username;
    private String password;

    private List<Board> boards;
}