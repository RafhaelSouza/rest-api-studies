package com.studies.foodorders.api.model.restaurant;

import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.model.localization.address.AddressModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Setter
@Getter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    @ApiModelProperty(example = "1")
    //@JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    //@JsonView({ RestaurantView.Summary.class, RestaurantView.IdAndName.class })
    private String name;

    @ApiModelProperty(example = "5.00")
    //@JsonView({ RestaurantView.Summary.class })
    private BigDecimal shippingCosts;

    //@JsonView({ RestaurantView.Summary.class })
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean opened;
    private AddressModel address;

}
