package com.personalprojects.myfilms.myfilms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import com.personalprojects.myfilms.myfilms.controller.StreamingServiceController;
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
	
	// returns all streaming services
	public List<StreamingService> listAll(){
		List<StreamingService> allStreamingServices = streamingServiceRepository.findAll();
		
		//HATEOAS
		for(StreamingService streamingService : allStreamingServices) {
			Long id = streamingService.getId();
			streamingService.add(linkTo(methodOn(StreamingServiceController.class)
					.findStreamingServiceById(id)).withSelfRel());
		}
		
		return allStreamingServices;
	}	
	
	// Find streaming service by id or throw exception
	public StreamingService findStreamingServiceByIdOrThrowBadRequestException(Long id) {
		StreamingService result = streamingServiceRepository.findById(id).orElseThrow(() -> new BadRequestException("Streaming Service Not Found"));
		
		// HATEOAS
		result.add(linkTo(methodOn(StreamingServiceController.class)
				.allStreamingServices()).withRel("allStreamingsService"));
		
		return result;
	}
	
	// persists new streaming service in the database
	public StreamingService save(StreamingServicePostRequestBody streamingServicePostRequestBody){
		StreamingService streamingService = StreamingServiceMapper.INSTANCE.toStreamingService(streamingServicePostRequestBody);
		return streamingServiceRepository.save(streamingService);
	}
	
	// removes a streaming service in the database
	public void delete(Long id) {
		streamingServiceRepository.delete(findStreamingServiceByIdOrThrowBadRequestException(id));
	}
	
	
	// update streaming service
	public void replace(StreamingServicePutRequestBody streamingServicePutRequestBody) {
		StreamingService savedStreamingService = findStreamingServiceByIdOrThrowBadRequestException(streamingServicePutRequestBody.getId());
		StreamingService streamingService = StreamingServiceMapper.INSTANCE.toStreamingService(streamingServicePutRequestBody);
		streamingService.setId(savedStreamingService.getId());
		streamingServiceRepository.save(streamingService);		
	}
	
}