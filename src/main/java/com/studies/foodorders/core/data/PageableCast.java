package com.studies.foodorders.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

public class PageableCast {

	public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
		var sorts = pageable.getSort().stream()
			.filter(sort -> fieldsMapping.containsKey(sort.getProperty()))
			.map(order -> new Sort.Order(order.getDirection(), 
					fieldsMapping.get(order.getProperty())))
			.collect(Collectors.toList());
							
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(sorts));
	}
	
}
