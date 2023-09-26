package com.studies.foodorders.api.model.restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.model.localization.address.AddressModel;
import com.studies.foodorders.api.model.restaurant.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantModel {

    @ApiModelProperty(example = "1")
    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    @JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private String name;

    @ApiModelProperty(example = "5.00")
    @JsonView({ RestaurantView.Summary.class })
    private BigDecimal shippingCosts;

    @JsonView({ RestaurantView.Summary.class })
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean opened;
    private AddressModel address;

}
