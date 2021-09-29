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
import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.repository.FilmRepository;
import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.FilmPutRequestBody;
import com.personalprojects.myfilms.myfilms.util.FilmCreator;
import com.personalprojects.myfilms.myfilms.util.FilmPostRequestBodyCreator;
import com.personalprojects.myfilms.myfilms.util.FilmPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class FilmServiceTest {	
	@InjectMocks
	private FilmService filmService;
	
	@Mock
	private FilmRepository filmRepositoryMock;
	
	@BeforeEach
	void setUp() {
		Film film = FilmCreator.createFilm();
		Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		
		BDDMockito.when(filmRepositoryMock.findById(film.getId())).thenReturn(Optional.of(film));
		BDDMockito.when(filmRepositoryMock.save(filmToBeSaved)).thenReturn(film);
		BDDMockito.when(filmRepositoryMock.findAll()).thenReturn(List.of(film));					
	}

	@Test
	@DisplayName("findFilmByIdOrThrowBadRequestException returns film by Id when successful")
	void findFilmByIdOrThrowBadRequestException_ReturnsFilmById_WhenSuccessful() {
		Film film = FilmCreator.createFilm();
		Film filmFound = filmRepositoryMock.findById(film.getId()).orElseThrow(() -> new BadRequestException("Film Not Found"));
		
		Film filmReturned = filmService.findFilmByIdOrThrowBadRequestException(filmFound.getId());
		
		Assertions.assertThat(filmFound).isEqualTo(filmReturned);
	}
	
	@Test
	@DisplayName("save persists film when successful")
	void save_PersistsFilm_WhenSuccessful() {
		FilmPostRequestBody filmToBeSaved = FilmPostRequestBodyCreator.createFilmPostRequestBody();
		Film filmSaved = filmService.save(filmToBeSaved);
		
		Assertions.assertThat(filmSaved.getName()).isEqualTo(filmToBeSaved.getName());
	}
	
	@Test
	@DisplayName("listAll returns all films in a list when successful")
	void listAll_ReturnsAllFilmsInAList_WhenSuccessful() {
		List<Film> allFilms = filmService.listAll();
		
		Assertions.assertThat(allFilms).isNotEmpty().isNotNull();
		Assertions.assertThat(allFilms).hasSize(1);
	}
	
	@Test
	@DisplayName("delete removes film when successful")
	void delete_RemovesFilm_WhenSuccessful() {
		Film filmToBeDeleted = FilmCreator.createFilmWithoutId();
		Film filmSaved = filmRepositoryMock.save(filmToBeDeleted);
		
		Film filmFound = filmService.findFilmByIdOrThrowBadRequestException(filmSaved.getId());		
		
		Assertions.assertThatCode(() -> filmService.delete(filmFound.getId())).isNull();
		Assertions.assertThatCode(() -> filmService.delete(filmFound.getId())).doesNotThrowAnyException();
	}
	
	@Test
	@DisplayName("delete throws BadRequestException when film is not found")
	void delete_ThrowsBadRequestException_WhenFilmIsNotFound() {
		Film film2 = Film.builder()
							.id(2L)
							.name("Matrix")
							.releaseYear(1999)
							.build();
		
		Assertions.assertThatCode(() -> filmService.delete(film2.getId())).isNotNull();
		Assertions.assertThatCode(() -> filmService.delete(film2.getId())).isInstanceOf(BadRequestException.class);		
	}
	
	@Test
	@DisplayName("replace updates film when successful")
	void replace_UpdatesFilm_WhenSuccessful() {
		FilmPostRequestBody filmPostRequestBody = FilmPostRequestBodyCreator.createFilmPostRequestBody();
		Film savedFilm = filmService.save(filmPostRequestBody);

		FilmPutRequestBody filmPutRequestBody = FilmPutRequestBodyCreator.createFilmPutRequestBody();
		filmPutRequestBody.setName("Matrix");
		filmService.replace(filmPutRequestBody);
		
		
		Assertions.assertThatCode(() -> filmService.replace(FilmPutRequestBodyCreator.createFilmPutRequestBody())).isNull();
		Assertions.assertThatCode(() -> filmService.replace(FilmPutRequestBodyCreator.createFilmPutRequestBody())).doesNotThrowAnyException();
		Assertions.assertThat(savedFilm.getId()).isEqualTo(filmPutRequestBody.getId());
	}
}
