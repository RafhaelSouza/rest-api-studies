package com.studies.foodorders.api.v1.links;

import com.studies.foodorders.api.v1.controllers.sells.StatisticsController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StatisticLinks {

    public Link linkToStatistics(String rel) {
        return linkTo(StatisticsController.class).withRel(rel);
    }

    public Link linkToStatisticsSailySells(String rel) {
        TemplateVariables filtroVariables = new TemplateVariables(
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("creationDateStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("creationDateEnd", TemplateVariable.VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(methodOn(StatisticsController.class)
                .searchDailySales(null)).toUri().toString();

        return new Link(UriTemplate.of(ordersUrl, filtroVariables), rel);
    }

}
