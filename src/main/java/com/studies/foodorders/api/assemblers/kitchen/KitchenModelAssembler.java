package com.studies.foodorders.api.assemblers.kitchen;

import com.studies.foodorders.api.controllers.kitchen.KitchenController;
import com.studies.foodorders.api.model.kitchen.KitchenInput;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenModelAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModelAssembler() {
        super(KitchenController.class, KitchenModel.class);
    }

    public KitchenModel toModel(Kitchen kitchen) {

        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(linkTo(KitchenController.class).withRel("kitchens"));

        return modelMapper.map(kitchen, KitchenModel.class);
    }

    public Kitchen toDomainObject(KitchenInput kitchenInput) {
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObject(KitchenInput kitchenInput, Kitchen kitchen) {
        modelMapper.map(kitchenInput, kitchen);
    }

}
