package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmPutRequestBody {	
	@NotEmpty	
	private Long id;
	
	@NotEmpty
	private String name;
	
	private Integer releaseYear;
}
