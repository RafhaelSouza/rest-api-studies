package com.studies.foodorders.api.v1.assemblers.kitchen;

import com.studies.foodorders.api.v1.controllers.kitchen.KitchenController;
import com.studies.foodorders.api.v1.links.KitchenLinks;
import com.studies.foodorders.api.v1.models.kitchen.KitchenInput;
import com.studies.foodorders.api.v1.models.kitchen.KitchenModel;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenModelAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KitchenLinks kitchenLinks;

    public KitchenModelAssembler() {
        super(KitchenController.class, KitchenModel.class);
    }

    public KitchenModel toModel(Kitchen kitchen) {

        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(kitchenLinks.linkToKitchens("kitchens"));

        return kitchenModel;
    }

    public Kitchen toDomainObject(KitchenInput kitchenInput) {
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObject(KitchenInput kitchenInput, Kitchen kitchen) {
        modelMapper.map(kitchenInput, kitchen);
    }

}
