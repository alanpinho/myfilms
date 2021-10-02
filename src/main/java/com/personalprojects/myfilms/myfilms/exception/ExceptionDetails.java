package com.personalprojects.myfilms.myfilms.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {
	protected String title;
	protected Integer status;
	protected String details;
	protected String developerMessage;
	protected LocalDateTime timestamp;
}
