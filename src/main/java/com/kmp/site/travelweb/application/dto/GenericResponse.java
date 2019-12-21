package com.kmp.site.travelweb.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse {

	@JsonProperty("code")
	private int code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private int type;

	public GenericResponse() {
		this.code = 200;
		this.message = "Ok";
		this.type = 1;
	}

	public GenericResponse(String message, int code) {
		this.type = -1;
		this.code = code;
		this.message = message;
	}
	
}
