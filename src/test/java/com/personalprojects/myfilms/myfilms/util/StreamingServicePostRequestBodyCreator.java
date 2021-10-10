package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.requests.StreamingServicePostRequestBody;

public class StreamingServicePostRequestBodyCreator {
	
	public static StreamingServicePostRequestBody createStreamingServicePostRequestBody() {
		return StreamingServicePostRequestBody.builder()
				.name(StreamingServiceCreator.createStreamingService().getName())
				.build();
	}
}
