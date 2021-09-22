package com.personalprojects.myfilms.myfilms.requests;

import lombok.Data;

@Data
public class FilmPutRequestBody {
	private Long id;
	private String name;
	private Integer releaseYear;
}
