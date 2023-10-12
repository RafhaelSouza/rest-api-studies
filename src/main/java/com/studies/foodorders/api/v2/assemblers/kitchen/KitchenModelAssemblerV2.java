package com.studies.foodorders.api.v2.assemblers.kitchen;

import com.studies.foodorders.api.v2.controllers.kitchen.KitchenControllerV2;
import com.studies.foodorders.api.v2.links.KitchenLinksV2;
import com.studies.foodorders.api.v2.models.kitchen.KitchenInputV2;
import com.studies.foodorders.api.v2.models.kitchen.KitchenModelV2;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenModelAssemblerV2 extends RepresentationModelAssemblerSupport<Kitchen, KitchenModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KitchenLinksV2 kitchenLinks;

    public KitchenModelAssemblerV2() {
        super(KitchenControllerV2.class, KitchenModelV2.class);
    }

    public KitchenModelV2 toModel(Kitchen kitchen) {

        KitchenModelV2 kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(kitchenLinks.linkToKitchens("kitchens"));

        return kitchenModel;
    }

    public Kitchen toDomainObject(KitchenInputV2 kitchenInput) {
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObject(KitchenInputV2 kitchenInput, Kitchen kitchen) {
        modelMapper.map(kitchenInput, kitchen);
    }

}
