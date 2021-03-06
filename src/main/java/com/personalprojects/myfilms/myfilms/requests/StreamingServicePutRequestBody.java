package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamingServicePutRequestBody {
	
	@NotNull
	private Long id;
	
	@NotEmpty
	private String name;
	
}
