package com.personalprojects.myfilms.myfilms.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.personalprojects.myfilms.myfilms.exception.BadRequestException;
import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.util.FilmCreator;

@DataJpaTest
@DisplayName("Unit tests for FilmRepository")
class FilmRepositoryTest {
	
	@Autowired
	private FilmRepository filmRepository;

	@Test
	@DisplayName("save persists film when successful")
	void save_PersistsFilm_WhenSuccessful() {
		  Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		  Film filmSaved = this.filmRepository.save(filmToBeSaved);
		  
		  Assertions.assertThat(filmSaved).isNotNull();
		  Assertions.assertThat(filmSaved.getId()).isNotNull();
		  Assertions.assertThat(filmSaved.getName()).isEqualTo(filmToBeSaved.getName());
	}
	
	@Test
	@DisplayName("save updates film when successful")
	void save_UpdatesFilm_WhenSuccessful() {
		  Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		  Film filmSaved = this.filmRepository.save(filmToBeSaved);
		  
		  filmSaved.setName("Matrix");
		  
		  Film updatedFilm = this.filmRepository.save(filmSaved);
		  
		  Assertions.assertThat(updatedFilm).isNotNull();
		  Assertions.assertThat(updatedFilm.getId()).isNotNull();
		  Assertions.assertThat(updatedFilm.getName()).isEqualTo(filmSaved.getName()).isEqualTo("Matrix");
	}
	
	@Test
	@DisplayName("findById returns Film by Id")
	void findById_ReturnsFilmById_WhenSuccessful() {
		Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		Film filmSaved = this.filmRepository.save(filmToBeSaved);
		
		Film filmReturned = this.filmRepository.findById(filmSaved.getId()).orElseThrow(() -> new BadRequestException("Film not found"));
		
		Assertions.assertThat(filmReturned).isEqualTo(filmSaved);
		Assertions.assertThat(filmReturned.getId())
											.isEqualTo(filmSaved.getId())
											.isEqualTo(filmToBeSaved.getId())
											.isNotNull();
	}
	
	@Test
	@DisplayName("findById returns null when Id is not found")
	void findById_ReturnsNull_WhenIdIsNotFound() {

		Assertions.assertThatCode(() -> this.filmRepository.findById(2654915L)).isNull();
	}
		
	@Test
	@DisplayName("findAll lists all films when successful")
	void findAll_ListsAllFilms_WhenSuccessful() {
		Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		Film filmSaved = this.filmRepository.save(filmToBeSaved);
			
		List<Film> listOfAllFilms = this.filmRepository.findAll();
		
		Assertions.assertThat(listOfAllFilms).hasSize(1);
		Assertions.assertThat(listOfAllFilms.get(0).getName()).isEqualTo(filmSaved.getName());
		Assertions.assertThat(listOfAllFilms).isNotNull();		
	}
	
	@Test
	@DisplayName("delete removes film when successful")
	void delete_RemovesFilm_WhenSuccessful() {
		Film filmToBeSaved = FilmCreator.createFilmWithoutId();
		Film filmSaved = this.filmRepository.save(filmToBeSaved);
		
		this.filmRepository.delete(filmSaved);
		
		Optional<Film> film = this.filmRepository.findById(filmSaved.getId());
		
		Assertions.assertThat(film).isEmpty();
		Assertions.assertThatCode(() -> this.filmRepository.delete(filmSaved)).isNull();		
		Assertions.assertThatCode(() -> this.filmRepository.delete(filmSaved)).doesNotThrowAnyException();		
	}	
}
