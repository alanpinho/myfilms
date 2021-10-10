package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.model.StreamingService;

public class StreamingServiceCreator {
	public static StreamingService createStreamingService() {
		return StreamingService.builder()
				.id(1L)
				.name("Netflix")				
				.build();
	}
	
	public static StreamingService createStreamingServiceWithoutId() {
		return StreamingService.builder()
				.name("Netflix")
				.build();
	}
	
	public static StreamingService createStreamingServiceWithoutName() {
		return StreamingService.builder()
				.id(1L)
				.build();
	}
	
	public static StreamingService createValidUpdatedStreamingService() {
		return StreamingService.builder()
				.id(1L)
				.name("Amazon Prime Video")
				.build();
	}
}
