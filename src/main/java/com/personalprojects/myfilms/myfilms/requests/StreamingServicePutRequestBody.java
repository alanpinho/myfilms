package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StreamingServicePutRequestBody {
	
	@NotNull
	private Long id;
	
	@NotEmpty
	private String name;
	
}
