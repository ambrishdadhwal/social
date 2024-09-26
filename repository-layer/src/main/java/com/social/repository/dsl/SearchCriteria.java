package com.social.repository.dsl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class SearchCriteria
{

	private String key;
	private String operator;
	private String value;
}
