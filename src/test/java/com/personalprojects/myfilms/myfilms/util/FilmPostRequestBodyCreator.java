package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;

public class FilmPostRequestBodyCreator {
	
	public static FilmPostRequestBody createFilmPostRequestBody() {
		return FilmPostRequestBody.builder()
				.name(FilmCreator.createFilm().getName())
				.releaseYear(FilmCreator.createFilm().getReleaseYear())
				.build();
	}
}
