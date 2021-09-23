import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.personalprojects.myfilms.myfilms.controller.FilmController;
import com.personalprojects.myfilms.myfilms.model.Film;
import com.personalprojects.myfilms.myfilms.service.FilmService;
import com.personalprojects.myfilms.myfilms.util.FilmCreator;
import com.personalprojects.myfilms.myfilms.util.FilmPostRequestBodyCreator;
import com.personalprojects.myfilms.myfilms.util.FilmPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class FilmControllerTest {
	
	@InjectMocks
	private FilmController filmController;
	
	@Mock
	private FilmService filmServiceMock;
	
	@BeforeEach
	void setUp() {
		List<Film> allFilms = new ArrayList<>(List.of(FilmCreator.createFilm()));
		BDDMockito.when(filmServiceMock.listAll()).thenReturn(allFilms);
		BDDMockito.when(filmServiceMock.findFilmByIdOrThrowBadRequestException(ArgumentMatchers.anyLong())).thenReturn(FilmCreator.createFilm());
		BDDMockito.when(filmServiceMock.save(FilmPostRequestBodyCreator.createFilmPostRequestBody())).thenReturn(FilmCreator.createFilm());
		BDDMockito.when(filmServiceMock.save(ArgumentMatchers.any())).thenReturn(FilmCreator.createFilm());
		BDDMockito.doNothing().when(filmServiceMock).delete(ArgumentMatchers.anyLong());
		BDDMockito.doNothing().when(filmServiceMock).replace(FilmPutRequestBodyCreator.createFilmPutRequestBody());
	}
	

	@Test
	@DisplayName("Returns All Films")
	void listAllFilms_ReturnAllFilms_WhenSuccessful() {
		List<Film> expectedFilms = filmController.listAllFilms().getBody();
		
		List<Film> films = new ArrayList<>();
		
		films.add(FilmCreator.createFilm());
		
		Assertions.assertThat(expectedFilms).isEqualTo(films);	
		Assertions.assertThat(expectedFilms).isNotNull().isNotEmpty().hasSize(1);
	}
	
	@Test
	@DisplayName("Returns film by Id when successful")
	void findFilmById_ReturnFilmById_WhenSuccessful() {
		Film filmFound = filmController.findFilmById(1L).getBody();
		Film expectedFilm = FilmCreator.createFilm();
		
		Assertions.assertThat(expectedFilm).isEqualTo(filmFound);
		Assertions.assertThat(filmFound).isNotNull();
		Assertions.assertThat(filmFound.getId()).isEqualTo(1L);
		Assertions.assertThat(filmFound.getId()).isEqualTo(expectedFilm.getId());
	}
	
	@Test
	@DisplayName("Persists film in the database when successful")
	void save_PersistsFilmInTheDatabase_WhenSuccessful() {
		Film createdFilm = filmController.save(FilmPostRequestBodyCreator.createFilmPostRequestBody()).getBody();
		Film expectedFilm = FilmCreator.createFilm();
		
		Assertions.assertThat(createdFilm).isEqualTo(expectedFilm);
		Assertions.assertThat(createdFilm.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Removes Film from database when successful")
	void delete_RemovesFilmFromDatabase_WhenSuccessful() {
		/*
		 * Linha abaixo comentada para explicar o que estamos comparando 
		 * */
		
		// ResponseEntity<Void> entityStatusCode = filmController.deleteFilm(1L);
		
		Assertions.assertThatCode(() -> filmController.deleteFilm(1L)).doesNotThrowAnyException();
		Assertions.assertThat(filmController.deleteFilm(1L).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	@DisplayName("replace updates film when successful")
	void replace_UpdatesFilm_WhenSuccessful() {
		Assertions.assertThatCode(() -> filmController.replace(FilmPutRequestBodyCreator.createFilmPutRequestBody()))
														.doesNotThrowAnyException();
		
		ResponseEntity<Void> entity = filmController.replace(FilmPutRequestBodyCreator.createFilmPutRequestBody());
		
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
