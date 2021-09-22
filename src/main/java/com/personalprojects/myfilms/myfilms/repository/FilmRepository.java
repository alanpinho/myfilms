package com.personalprojects.myfilms.myfilms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalprojects.myfilms.myfilms.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>{

}
