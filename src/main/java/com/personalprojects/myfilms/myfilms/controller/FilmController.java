package com.personalprojects.myfilms.myfilms.controller;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/films")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	@Operation(summary = "Returns a List with all films in the database")
	@ApiResponses(
		@ApiResponse(responseCode = "200", description="When the operation is successful"))
	public ResponseEntity<List<Film>> listAllFilms(){
		return new ResponseEntity<>(filmService.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Returns a film by ID")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description="When the film exists in the database"),
			@ApiResponse(responseCode="400", description="When the film does not exist in the database")})
	public ResponseEntity<Film> findFilmById(@PathVariable Long id){
		Film result = filmService.findFilmByIdOrThrowBadRequestException(id);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/new")
	@Operation(summary = "Add a new film in the database")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description="When the film is saved in the database"),
		@ApiResponse(responseCode="400", description="When field name is missing")})
	public ResponseEntity<Film> save(@Valid @RequestBody FilmPostRequestBody filmPostRequestBody){
		return new ResponseEntity<>(filmService.save(filmPostRequestBody), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Remove a film by ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description="When the operation is successful"),
		@ApiResponse(responseCode="400", description="When the film does not exist in the database")})
	public ResponseEntity<Void> deleteFilm(@PathVariable Long id){
		filmService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	@Operation(summary = "Update the film's data")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description="When the operation is successful"),
		@ApiResponse(responseCode="400", description="When the film's ID does not exist in the database")})
	public ResponseEntity<Void> replace(@Valid @RequestBody FilmPutRequestBody filmPutRequestBody){
		filmService.replace(filmPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
