package com.studies.foodorders.api.assemblers.kitchen;

import com.studies.foodorders.api.model.kitchen.KitchenInput;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModel toModel(Kitchen kitchen) {
        return modelMapper.map(kitchen, KitchenModel.class);
    }

    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {

        return kitchens.stream()
                .map(kitchen -> toModel(kitchen))
                .collect(Collectors.toList());

    }

    public Kitchen toDomainObject(KitchenInput kitchenInput) {
        return modelMapper.map(kitchenInput, Kitchen.class);
    }

    public void copyToDomainObject(KitchenInput kitchenInput, Kitchen kitchen) {
        modelMapper.map(kitchenInput, kitchen);
    }

}
