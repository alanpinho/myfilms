package com.personalprojects.myfilms.myfilms.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.personalprojects.myfilms.myfilms.model.StreamingService;
import com.personalprojects.myfilms.myfilms.service.StreamingServiceService;
import com.personalprojects.myfilms.myfilms.util.StreamingServiceCreator;
import com.personalprojects.myfilms.myfilms.util.StreamingServicePostRequestBodyCreator;
import com.personalprojects.myfilms.myfilms.util.StreamingServicePutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class StreamingServiceControllerTest {

	@InjectMocks
	private StreamingServiceController streamingServiceController;

	@Mock
	private StreamingServiceService streamingServiceServiceMock;

	@BeforeEach
	void setUp() {		
		List<StreamingService> allStreamingServices = new ArrayList<>();		
		StreamingService streamingService = StreamingServiceCreator.createStreamingService();	
		allStreamingServices.add(streamingService);

		BDDMockito.when(streamingServiceServiceMock.listAll()).thenReturn(List.of(streamingService));
		BDDMockito.when(streamingServiceServiceMock.findStreamingServiceByIdOrThrowBadRequestException(ArgumentMatchers.any())).thenReturn(streamingService);
		BDDMockito.when(streamingServiceServiceMock.save(ArgumentMatchers.any())).thenReturn(StreamingServiceCreator.createStreamingService());
		BDDMockito.doNothing().when(streamingServiceServiceMock).delete(ArgumentMatchers.anyLong());
	}
	
	@Test
	@DisplayName("allStreamingServices returns a list of streaming services when successful")
	void allStreamingServices_ReturnsAListOfStreamingServices_WhenSuccessful() {		
		StreamingService streamingServiceExpected = StreamingServiceCreator.createStreamingService();
		List<StreamingService> listExpected = new ArrayList<>();
		listExpected.add(streamingServiceExpected);

		ResponseEntity<List<StreamingService>>  streamingServiceResponse = streamingServiceController.allStreamingServices();		

		Assertions.assertThat(streamingServiceResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(streamingServiceResponse.getBody()).hasSize(1);
		Assertions.assertThat(streamingServiceResponse.getBody()).isEqualTo(listExpected);		
	}

	@Test
	@DisplayName("findStreamingServiceById returns streaming service by id whenSuccessful")
	void findStreamingServiceById_ReturnStreamingServiceById_WhenSuccessful() {				
		String expectedName = StreamingServiceCreator.createStreamingService().getName();		

		ResponseEntity<StreamingService> streamingServiceResponse = streamingServiceController.findStreamingServiceById(1L);		

		Assertions.assertThat(streamingServiceResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(streamingServiceResponse.getBody().getId()).isEqualTo(1L);
		Assertions.assertThat(streamingServiceResponse.getBody().getName()).isEqualTo(expectedName);
		Assertions.assertThat(streamingServiceResponse).isNotNull();		
	}

	@Test
	@DisplayName("save persists streaming service in the database when successful")
	void save_PersistsStreamingServiceInTheDB_WhenSuccessful() {		
		StreamingService streamingServiceExpected = StreamingServiceCreator.createStreamingService();

		ResponseEntity<StreamingService> streamingServiceResponse = streamingServiceController.save(StreamingServicePostRequestBodyCreator
				.createStreamingServicePostRequestBody());		

		Assertions.assertThat(streamingServiceResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(streamingServiceResponse.getBody()).isNotNull().isEqualTo(streamingServiceExpected);
	}

	@Test
	@DisplayName("deleteStreamingService removes streaming service from database when successful")
	void deleteStreamingService_RemovesStreamingServiceFromDB_WhenSuccessful() {	

		ResponseEntity<Void> streamingServiceResponse = streamingServiceController.deleteStreamingService(1L);

		Assertions.assertThat(streamingServiceResponse.getStatusCode())
		.isEqualTo(HttpStatus.NO_CONTENT);

		Assertions.assertThatCode(() -> streamingServiceController.deleteStreamingService(1L)).doesNotThrowAnyException();
		Assertions.assertThat(streamingServiceResponse.getBody()).isNull();
	}

	@Test
	@DisplayName("replace updates streaming service when successful")
	void replace_UpdatesStreamingService_WhenSuccessful() {

		ResponseEntity<Void> streamingServiceResponse = streamingServiceController
				.replace(StreamingServicePutRequestBodyCreator.createStreamingServicePutRequestBody());

		Assertions.assertThat(streamingServiceResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		
		Assertions.assertThatCode(() -> streamingServiceController
				.replace(StreamingServicePutRequestBodyCreator.createStreamingServicePutRequestBody()))
		.doesNotThrowAnyException();
	}
}
