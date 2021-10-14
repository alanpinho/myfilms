package com.personalprojects.myfilms.myfilms.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.personalprojects.myfilms.myfilms.controller.FilmController;
import com.personalprojects.myfilms.myfilms.exception.BadRequestException;
import com.personalprojects.myfilms.myfilms.mapper.FilmMapper;
import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.repository.FilmRepository;
import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.FilmPutRequestBody;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	public Film findFilmByIdOrThrowBadRequestException(Long id) { 
		Pageable pageable = null;

		Film result = filmRepository.findById(id).orElseThrow(() -> new BadRequestException("Film Not Found"));

		// HATEOAS
		result.add(linkTo(methodOn(FilmController.class).listAllFilms(pageable)).withRel("allFilms"));

		return result;
	}

	public Film save(FilmPostRequestBody filmPostRequestBody) {
		Film film = FilmMapper.INSTANCE.toFilm(filmPostRequestBody);		
		return filmRepository.save(film);

	}

	public Page<Film> listAll(Pageable pageable) {

		Page<Film> films = filmRepository.findAll(pageable);

		// HATEOAS
		for(Film film : films) {
			Long id = film.getId();
			film.add(linkTo(methodOn(FilmController.class).findFilmById(id)).withSelfRel());
		}

		return films;
	}

	public void delete(Long id) {
		filmRepository.delete(findFilmByIdOrThrowBadRequestException(id));
	}

	public void replace(FilmPutRequestBody filmPutRequestBody) {
		Film savedFilm = findFilmByIdOrThrowBadRequestException(filmPutRequestBody.getId());
		Film film = FilmMapper.INSTANCE.toFilm(filmPutRequestBody);
		film.setId(savedFilm.getId());
		filmRepository.save(film);		
	}
}
