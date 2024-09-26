package com.social.repository.dsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.social.entity.ProfileE;

public class SearchCriteriaUtil
{

	public List<SearchCriteria> formatSearchCriteria(String[] filter)
	{
		List<SearchCriteria> criterias = new ArrayList<>();
		if (null != filter)
		{
			Collection<SearchCriteria> collect = Arrays.asList(filter).parallelStream().map(this::validateFilterPattern)
				.collect(Collectors.toList());
			criterias.addAll(collect);
		}
		return criterias;
	}

	private SearchCriteria validateFilterPattern(String filter)
	{
		final Pattern pattern = Pattern.compile("([\\w.]+?)(:|<|>|<=|>=|%|-|\\(\\))([\\w\\s\\(\\),.:-]+?)\\|");
		Matcher m = pattern.matcher(filter + "|");
		if (m.find())
		{
			return SearchCriteria.builder().key(m.group(1)).operator(m.group(2)).value(m.group(3)).build();
		}
		else
		{
			throw new RuntimeException("Invalid Filter format");
		}
	}

	public BooleanExpression getPCQFilterExp(List<SearchCriteria> criterias)
	{
		BooleanExpression exp = new CommonPredicateBuilder<>(ProfileE.class).build();

		return exp;
	}
}
