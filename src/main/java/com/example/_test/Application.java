package com.example._test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// : 스프링부트에 필요한 기본 설정을 제공

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// args
		// : 커맨드 라인의 인수들을 전달 만나서
		SpringApplication.run(Application.class, args);
	}
}