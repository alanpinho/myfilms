package com.personalprojects.myfilms.myfilms.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.repository.FilmRepository;
import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.util.FilmCreator;
import com.personalprojects.myfilms.myfilms.util.FilmPostRequestBodyCreator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FilmControllerIT {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Test
	@DisplayName("listAllFilms returns a list of films object when successful")
	void listAllFilms_ReturnsAListOfFilms_WhenSuccessful() {
		Film savedFilm = filmRepository.save(FilmCreator.createFilmWithoutId());
		
		Film expectedFilm = FilmCreator.createFilm();
		
		List<Film> allFilms = testRestTemplate.exchange("/films", HttpMethod.GET, null, new ParameterizedTypeReference<List<Film>>() {
		}).getBody();
		
		Assertions.assertThat(allFilms).isNotEmpty().isNotNull();
		Assertions.assertThat(savedFilm).isEqualTo(expectedFilm);
		Assertions.assertThat(allFilms).hasSize(1);
		Assertions.assertThat(allFilms.get(0).getName()).isEqualTo(expectedFilm.getName()).isEqualTo(savedFilm.getName());
	}
	
	@Test
	@DisplayName("findFilmById film by Id when successful")
	void findFilmById_ReturnFilmById_WhenSuccessful() {		
		Film savedFilm = filmRepository.save(FilmCreator.createFilmWithoutId());
		Long expectedId = savedFilm.getId();
		
		Film filmReturned = testRestTemplate.getForObject("/films/{id}",																
																Film.class, 
																expectedId);		
		
		Assertions.assertThat(savedFilm).isEqualTo(filmReturned);
		Assertions.assertThat(filmReturned).isNotNull();
		Assertions.assertThat(filmReturned.getId()).isEqualTo(expectedId);
		Assertions.assertThat(savedFilm.getId()).isEqualTo(filmReturned.getId());
	}
	
	@Test
	@DisplayName("save persists film in the database when successful")
	void save_PersistsFilmInTheDatabase_WhenSuccessful() {
		FilmPostRequestBody filmPostRequestBody = FilmPostRequestBodyCreator.createFilmPostRequestBody();
		
		ResponseEntity<Film> filmResponseEntity = testRestTemplate.postForEntity("/films/new", filmPostRequestBody, Film.class);
		
		Assertions.assertThat(filmResponseEntity).isNotNull();
		Assertions.assertThat(filmResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(filmResponseEntity.getBody()).isNotNull();
		Assertions.assertThat(filmResponseEntity.getBody().getId()).isNotNull();
	}
	
	@Test
	@DisplayName("delete removes Film from database when successful")
	void delete_RemovesFilmFromDatabase_WhenSuccessful() {
		
		Film savedFilm = filmRepository.save(FilmCreator.createFilmWithoutId());
		
		ResponseEntity<Void> filmResponseEntity = testRestTemplate.exchange("/films/{id}", 
				HttpMethod.DELETE, 
				null, 
				Void.class, 
				savedFilm.getId());		
		
		Assertions.assertThat(filmResponseEntity).isNotNull();
		Assertions.assertThat(filmResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	@DisplayName("replace updates film when successful")
	void replace_UpdatesFilm_WhenSuccessful() {
		
		Film filmSaved = filmRepository.save(FilmCreator.createFilm());
		filmSaved.setName("Matrix");
		
		ResponseEntity<Void> filmResponseEntity = testRestTemplate.exchange("/films", 
				HttpMethod.PUT, 
				new HttpEntity<>(filmSaved), 
				Void.class, 
				filmSaved.getId());	
		
		Assertions.assertThat(filmResponseEntity).isNotNull();
		Assertions.assertThat(filmResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
