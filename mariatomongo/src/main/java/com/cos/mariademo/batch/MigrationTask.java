package com.cos.mariademo.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.mariademo.web.dto.CMRespDto;


@Component
public class MigrationTask {


//	@Scheduled(fixedDelay = 1000)
//	public void test() {
//		System.out.println("실행됨");
//	}
	
	// 초 분 시간 일 월 주
	@Scheduled(cron = "0 19 13 * * *", zone = "Asia/Seoul")
	public void mariaToMongo() {
		RestTemplate rt = new RestTemplate();
		CMRespDto response = rt.getForObject("http://localhost:8000/mig/mongoUsers", CMRespDto.class);
	}
}
