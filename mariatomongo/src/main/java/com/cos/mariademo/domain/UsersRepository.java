package com.cos.mariademo.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
	List<Users> findByCreateDateBetween(LocalDateTime startDatetime, LocalDateTime endDatetime);
}
