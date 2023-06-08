package com.studies.foodorders.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ZeroValueIncludesDescriptionValidator.class })
public @interface ZeroValueIncludesDescription {

	String message() default "required description invalid";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String valueField();

	String descriptionField();

	String requiredDescription();
}
