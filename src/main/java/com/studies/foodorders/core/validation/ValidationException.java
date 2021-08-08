package com.studies.foodorders.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -1371739115036544344L;
    private BindingResult bindingResult;

}
