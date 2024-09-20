package com.diefthyntis.MinimumViableProduct.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
	private String emailaddress;
	private String password;
	private String pseudonym;
}
