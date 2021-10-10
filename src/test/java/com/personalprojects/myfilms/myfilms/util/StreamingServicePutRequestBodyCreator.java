package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.requests.StreamingServicePutRequestBody;

public class StreamingServicePutRequestBodyCreator {
	
	public static StreamingServicePutRequestBody createStreamingServicePutRequestBody() {
		return StreamingServicePutRequestBody.builder()
				.id(1L)
				.name(StreamingServiceCreator.createStreamingService().getName())
				.build();
	}
}
