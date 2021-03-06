package com.cos.mariademo.web;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mariademo.domain.MongoUsers;
import com.cos.mariademo.domain.MongoUsersRepository;
import com.cos.mariademo.domain.Users;
import com.cos.mariademo.domain.UsersRepository;
import com.cos.mariademo.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MongoUsersController {

	private final UsersRepository usersRepository;
	private final MongoUsersRepository mongoUsersRepository;

	@GetMapping("/mig/mongoUsers")
	public CMRespDto<?> migUsers() {

		// 날짜 LocalDate.now().minusDays(1) 어제
		LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
		LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

		
		// MariaDB 데이터 가져오기
		List<Users> usersList = usersRepository.findByCreateDateBetween(startDatetime, endDatetime);

		// MongoDB에 넣기 위해 파싱
		List<MongoUsers> mongoUsersList = usersList.stream().map((u) -> {
			return MongoUsers.builder().id(u.getId()).username(u.getUsername()).password(u.getPassword())
					.boards(u.getBoards())
					.createDate(u.getCreateDate().toString())
					.build();
		}).collect(Collectors.toList());

		// MongoDB에 save하기
		mongoUsersRepository.saveAll(mongoUsersList);

		return new CMRespDto<>(1, mongoUsersList);
	}
}
