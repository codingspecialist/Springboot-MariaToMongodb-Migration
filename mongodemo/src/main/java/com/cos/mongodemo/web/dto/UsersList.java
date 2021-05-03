package com.cos.mongodemo.web.dto;

import java.util.List;

import lombok.Data;

@Data
public class UsersList {
    List<UsersRespDto> data;
}
