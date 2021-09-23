package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.requests.FilmPutRequestBody;

public class FilmPutRequestBodyCreator {
	
	public static FilmPutRequestBody createFilmPutRequestBody() {
		return FilmPutRequestBody.builder()
				.id(FilmCreator.createFilm().getId())
				.name(FilmCreator.createFilm().getName())
				.releaseYear(FilmCreator.createFilm().getReleaseYear())
				.build();
	}
}
