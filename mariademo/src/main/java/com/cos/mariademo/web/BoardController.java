package com.cos.mariademo.web;

import com.cos.mariademo.domain.Board;
import com.cos.mariademo.domain.BoardRepository;
import com.cos.mariademo.domain.Users;
import com.cos.mariademo.web.dto.CMRespDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;

    @PostMapping("/board")
    public CMRespDto<?> boardSave(@RequestBody Board board) {
        board.setUser(new Users(1L, null, null, null));

        return new CMRespDto<>(1, boardRepository.save(board));
    }
}
