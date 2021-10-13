package com.personalprojects.myfilms.myfilms.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreamingServicePostRequestBody {
	
	@NotEmpty
	private String name;
	

}
