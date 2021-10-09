package com.personalprojects.myfilms.myfilms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalprojects.myfilms.myfilms.exception.BadRequestException;
import com.personalprojects.myfilms.myfilms.mapper.StreamingServiceMapper;
import com.personalprojects.myfilms.myfilms.model.StreamingService;
import com.personalprojects.myfilms.myfilms.repository.StreamingServiceRepository;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePutRequestBody;

@Service
public class StreamingServiceService {
	
	@Autowired
	private StreamingServiceRepository streamingServiceRepository;	
	
	public List<StreamingService> listAll(){
		List<StreamingService> allStreamingServices = streamingServiceRepository.findAll();
		return allStreamingServices;
	}	
	
	public StreamingService findStreamingServiceByIdOrThrowBadRequestException(Long id) {
		StreamingService result = streamingServiceRepository.findById(id).orElseThrow(() -> new BadRequestException("Streaming Service Not Found"));
		return result;
	}
	
	public StreamingService save(StreamingServicePostRequestBody streamingServicePostRequestBody){
		StreamingService streamingService = StreamingServiceMapper.INSTANCE.toStreamingService(streamingServicePostRequestBody);
		return streamingServiceRepository.save(streamingService);
	}
	
	public void delete(Long id) {
		streamingServiceRepository.delete(findStreamingServiceByIdOrThrowBadRequestException(id));
	}
	
	public void replace(StreamingServicePutRequestBody streamingServicePutRequestBody) {
		StreamingService savedStreamingService = findStreamingServiceByIdOrThrowBadRequestException(streamingServicePutRequestBody.getId());
		StreamingService streamingService = StreamingServiceMapper.INSTANCE.toStreamingService(streamingServicePutRequestBody);
		streamingService.setId(savedStreamingService.getId());
		streamingServiceRepository.save(streamingService);		
	}
	
}