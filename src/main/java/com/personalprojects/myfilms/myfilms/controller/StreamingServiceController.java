package com.personalprojects.myfilms.myfilms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalprojects.myfilms.myfilms.model.StreamingService;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePutRequestBody;
import com.personalprojects.myfilms.myfilms.service.StreamingServiceService;

@RestController
@RequestMapping("/streaming-services")
public class StreamingServiceController {	
	
	@Autowired
	private StreamingServiceService streamingServiceService;
	
	@GetMapping
	public ResponseEntity<List<StreamingService>> allStreamingServices(){
		return new ResponseEntity<>(streamingServiceService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StreamingService> findFilmById(@PathVariable Long id){
		StreamingService result = streamingServiceService.findStreamingServiceByIdOrThrowBadRequestException(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/new")
	public ResponseEntity<StreamingService> save(@RequestBody StreamingServicePostRequestBody streamingServicePostRequestBody){
		return new ResponseEntity<>(streamingServiceService.save(streamingServicePostRequestBody), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFilm(@PathVariable Long id){
		streamingServiceService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody StreamingServicePutRequestBody streamingServicePutRequestBody){
		streamingServiceService.replace(streamingServicePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}

}
