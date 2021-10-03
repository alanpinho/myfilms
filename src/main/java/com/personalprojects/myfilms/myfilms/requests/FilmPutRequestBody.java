package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmPutRequestBody {	
	@NotNull
	@Schema(description="The film's ID that you want to update", example="3")
	private Long id;
	
	@NotEmpty
	@Schema(description="The film's name that you want to watch", example="Interstellar")
	private String name;
	
	@Schema(description="When the film was released", example="2014")
	private Integer releaseYear;
}
