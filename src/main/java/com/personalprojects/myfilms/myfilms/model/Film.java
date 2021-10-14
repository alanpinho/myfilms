package com.personalprojects.myfilms.myfilms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tb_film")
public class Film extends RepresentationModel<Film>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="The film's ID", example="1")
	private Long id;
	
	@Schema(description="The film's name", example="Matrix")
	private String name;
	
	@Schema(description="The film's release year", example="1999")
	private Integer releaseYear;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "streaming_service_id")
	private StreamingService streamingService;
	
}
