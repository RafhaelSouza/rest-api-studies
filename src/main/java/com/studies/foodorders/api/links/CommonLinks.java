package com.studies.foodorders.api.links;

import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;

public class CommonLinks {

    public static final TemplateVariables VARIABLES_PAGINATION = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));

}
