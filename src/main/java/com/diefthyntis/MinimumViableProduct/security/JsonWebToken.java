package com.diefthyntis.MinimumViableProduct.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JsonWebToken {
	private final String content;
	
}
