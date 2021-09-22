package com.personalprojects.myfilms.myfilms.requests;

import lombok.Data;

@Data
public class FilmPostRequestBody {

	private String name;
	private Long releaseYear;
	
	public FilmPostRequestBody(String name, Long releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}
}
