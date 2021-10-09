package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class StreamingServicePostRequestBody {
	
	@NotEmpty
	private String name;
	
}
