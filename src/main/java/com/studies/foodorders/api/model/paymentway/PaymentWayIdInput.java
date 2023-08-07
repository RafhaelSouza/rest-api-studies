package com.studies.foodorders.api.model.paymentway;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PaymentWayIdInput {

    @NotNull
    private Long id;

}
