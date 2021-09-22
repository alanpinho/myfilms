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

import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.FilmPutRequestBody;
import com.personalprojects.myfilms.myfilms.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public ResponseEntity<List<Film>> allFilms(){
		return new ResponseEntity<>(filmService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Film> findFilmById(@PathVariable Long id){
		Film result = filmService.findFilmByIdOrThrowBadRequestException(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Film> save(@RequestBody FilmPostRequestBody filmPostRequestBody){
		return new ResponseEntity<>(filmService.save(filmPostRequestBody), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFilm(@PathVariable Long id){
		filmService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody FilmPutRequestBody filmPutRequestBody){
		filmService.replace(filmPutRequestBody);
		return null;
	}
	
}
