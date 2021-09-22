package com.personalprojects.myfilms.myfilms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.requests.FilmPostRequestBody;
import com.personalprojects.myfilms.myfilms.requests.FilmPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {
	
	public static final FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
	
	public abstract Film toFilm(FilmPostRequestBody filmPostRequestBody);
	
	public abstract Film toFilm(FilmPutRequestBody FilmPutRequestBody);
}
