package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmPostRequestBody {	
	
	@NotEmpty
	@Schema(description="The film's name that you want to watch", example="Interstellar")
	private String name;
	
	@Schema(description="When the film was released", example="2014")
	private Integer releaseYear;
	
	public FilmPostRequestBody(String name, Integer releaseYear) {
		this.name = name;
		this.releaseYear = releaseYear;
	}
}
