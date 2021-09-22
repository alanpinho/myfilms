package com.personalprojects.myfilms.myfilms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.service.FilmService;

@SpringBootApplication
public class MyfilmsApplication implements CommandLineRunner{
	
	@Autowired
	private FilmService filmService;

	public static void main(String[] args) {
		SpringApplication.run(MyfilmsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		FilmPostRequestBody f1 = new FilmPostRequestBody("Matrix", 2001L);
		FilmPostRequestBody f2 = new FilmPostRequestBody("Matrix Reloaded", 2002L);
		
		filmService.save(f1);
		filmService.save(f2);
	}
}
