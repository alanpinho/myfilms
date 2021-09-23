package com.personalprojects.myfilms.myfilms.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmPutRequestBody {
	private Long id;
	private String name;
	private Integer releaseYear;
}
