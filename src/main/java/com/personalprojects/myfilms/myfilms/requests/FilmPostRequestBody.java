package com.personalprojects.myfilms.myfilms.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmPostRequestBody {

	private String name;
	private Integer releaseYear;
	
	public FilmPostRequestBody(String name, Integer releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}
}
