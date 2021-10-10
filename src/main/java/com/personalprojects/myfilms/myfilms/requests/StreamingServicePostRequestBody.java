package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamingServicePostRequestBody {
	
	@NotEmpty
	private String name;
	
}
