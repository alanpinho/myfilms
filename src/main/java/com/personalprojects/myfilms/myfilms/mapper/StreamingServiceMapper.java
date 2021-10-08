package com.personalprojects.myfilms.myfilms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.personalprojects.myfilms.myfilms.model.StreamingService;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class StreamingServiceMapper {
	
	public static final StreamingServiceMapper INSTANCE = Mappers.getMapper(StreamingServiceMapper.class);
	
	public abstract StreamingService toStreamingService(StreamingServicePostRequestBody streamingServicePostRequestBody);
	
	public abstract StreamingService toStreamingService(StreamingServicePutRequestBody streamingServicePutRequestBody);
}
