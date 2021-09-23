package com.personalprojects.myfilms.myfilms.util;

import com.personalprojects.myfilms.myfilms.model.Film;

public class FilmCreator {
	public static Film createFilm() {
		return Film.builder()
				.id(1L)
				.name("E o vento levou")
				.releaseYear(1940)
				.build();
	}
	
	public static Film createFilmWithoutId() {
		return Film.builder()
				.name("E o vento levou")
				.releaseYear(1940)
				.build();
	}
	
	public static Film createFilmWithoutName() {
		return Film.builder()
				.id(1L)
				.releaseYear(1940)
				.build();
	}
	
	public static Film createValidUpdatedFilm() {
		return Film.builder()
				.id(1L)
				.name("Matrix")
				.releaseYear(1999)
				.build();
	}
}
