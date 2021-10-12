package com.personalprojects.myfilms.myfilms.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.personalprojects.myfilms.myfilms.exception.BadRequestException;
import com.personalprojects.myfilms.myfilms.model.StreamingService;
import com.personalprojects.myfilms.myfilms.repository.StreamingServiceRepository;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.StreamingServicePutRequestBody;
import com.personalprojects.myfilms.myfilms.util.StreamingServiceCreator;
import com.personalprojects.myfilms.myfilms.util.StreamingServicePostRequestBodyCreator;
import com.personalprojects.myfilms.myfilms.util.StreamingServicePutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class StreamingServiceServiceTest {

	@InjectMocks
	private StreamingServiceService streamingServiceService;

	@Mock
	private StreamingServiceRepository streamingServiceRepositoryMock;

	@BeforeEach
	void setUp() {
		StreamingService streamingService = StreamingServiceCreator.createStreamingService();

		BDDMockito.when(streamingServiceRepositoryMock.findAll())
		.thenReturn(List.of(streamingService));		
		BDDMockito.when(streamingServiceRepositoryMock.findById(streamingService.getId()))
		.thenReturn(Optional.of(streamingService));		
		BDDMockito.when(streamingServiceRepositoryMock.save(StreamingServiceCreator.createStreamingServiceWithoutId()))
		.thenReturn(streamingService);		
		BDDMockito.doNothing().when(streamingServiceRepositoryMock).delete(streamingService);
	}

	@Test
	@DisplayName("listAll returns all streaming services in a List when successful")
	void listAll_ReturnsAllStreamingServices_WhenSuccessful() {
		StreamingService streamingServiceExpected = StreamingServiceCreator.createStreamingService();

		List<StreamingService> allStreamingServices = streamingServiceService.listAll();;

		Assertions.assertThat(allStreamingServices)
		.isNotEmpty()
		.isNotNull()
		.hasSize(1);	

		Assertions.assertThat(allStreamingServices.get(0).getName())
		.isEqualTo(streamingServiceExpected.getName());
	}

	@Test
	@DisplayName("findStreamingServiceByIdOrThrowBadRequestException returns streaming service by Id or throw BadRequestException")
	void findStreamingServiceByIdOrThrowBadRequestException_ReturnsS9treamingServiceById_WhenSuccessful() {
		StreamingService streamingServiceExpected = StreamingServiceCreator.createStreamingService();

		StreamingService streamingServiceFound = streamingServiceService
				.findStreamingServiceByIdOrThrowBadRequestException(1L);
		Assertions.assertThat(streamingServiceFound).isEqualTo(streamingServiceExpected);
		Assertions.assertThat(streamingServiceFound.getId()).isEqualTo(streamingServiceExpected.getId());
	}

	@Test
	@DisplayName("save persists streaming service in the database when successful")
	void save_PersistsStreamingService_WhenSuccessful() {
		StreamingService streamingServiceExpected = StreamingServiceCreator.createStreamingService();

		StreamingService streamingServiceSaved = streamingServiceService
				.save(StreamingServicePostRequestBodyCreator.createStreamingServicePostRequestBody());

		Assertions.assertThat(streamingServiceSaved).isEqualTo(streamingServiceExpected);
		Assertions.assertThat(streamingServiceSaved.getId()).isEqualTo(streamingServiceExpected.getId());
		Assertions.assertThat(streamingServiceSaved.getName()).isEqualTo(streamingServiceExpected.getName());
	}

	@Test
	@DisplayName("delete removes streaming service when successful")
	void delete_RemovesStreamingService_WhenSuccessful() {

		Assertions.assertThatCode(() -> streamingServiceService.delete(1L)).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> streamingServiceService.delete(1L)).isNull();
	}

	@Test
	@DisplayName("delete throws BadRequestException when streaming service is not found")
	void delete_ThrowsBadRequestException_WhenStreamingServiceIsNotFound() {
		StreamingService streamingServiceOutOfDB = StreamingService.builder()
				.id(2L)
				.name("HBO Max")
				.build();

		Assertions.assertThat(streamingServiceOutOfDB).isNotNull();
		Assertions.assertThatCode(() -> streamingServiceService.delete(streamingServiceOutOfDB.getId()))
		.isInstanceOf(BadRequestException.class);
	}

	@Test
	@DisplayName("replace updates streaming service when successful")
	void replace_UpdatesStreamingService_WhenSuccessful() {

		// Persist StreamingService in the database to be updated
		StreamingServicePostRequestBody streamingServiceToBeSaved = StreamingServicePostRequestBodyCreator.createStreamingServicePostRequestBody();
		StreamingService streamingServiceSaved = streamingServiceService.save(streamingServiceToBeSaved);

		// streamingServicePutRequestBody has the same Id as streamingServiceSaved. That's why we can replace it
		StreamingServicePutRequestBody streamingServicePutRequestBody = StreamingServicePutRequestBodyCreator.createStreamingServicePutRequestBody();
		streamingServicePutRequestBody.setName("Amazon Prime Video");		

		Assertions.assertThatCode(() -> streamingServiceService.replace(streamingServicePutRequestBody)).isNull();
		Assertions.assertThatCode(() -> streamingServiceService.replace(streamingServicePutRequestBody)).doesNotThrowAnyException();		
		Assertions.assertThat(streamingServiceSaved.getId()).isEqualTo(streamingServicePutRequestBody.getId());
		Assertions.assertThat(streamingServiceSaved.getName()).isNotEqualTo(streamingServicePutRequestBody.getName());
	}

}
