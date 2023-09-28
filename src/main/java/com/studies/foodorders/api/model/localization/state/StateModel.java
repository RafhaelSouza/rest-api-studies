package com.studies.foodorders.api.model.localization.state;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@ApiModel(value = "State", description = "State representation")
@Setter
@Getter
public class StateModel extends RepresentationModel<StateModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "California")
    private String name;

}
