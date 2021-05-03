package com.cos.mariademo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUsersRepository extends MongoRepository<MongoUsers, String>{
	
}
