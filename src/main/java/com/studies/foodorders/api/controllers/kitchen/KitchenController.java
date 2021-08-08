package com.studies.foodorders.api.controllers.kitchen;

import com.studies.foodorders.api.converter.kitchen.KitchenInputDisconverter;
import com.studies.foodorders.api.converter.kitchen.KitchenModelConverter;
import com.studies.foodorders.api.model.KitchensXmlWrapper;
import com.studies.foodorders.api.model.kitchen.KitchenInput;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.services.kitchen.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelConverter kitchenModelConverter;

    @Autowired
    private KitchenInputDisconverter kitchenInputDisconverter;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenService.list());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KitchenModel> list() {
        return kitchenModelConverter.toCollectionModel(kitchenService.list());
    }

    @GetMapping("/{id}")
    public KitchenModel find(@PathVariable Long id) {
        return kitchenModelConverter.toModel(kitchenService.findIfExists(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModel save(@RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenInputDisconverter.toDomainObject(kitchenInput);
        return kitchenModelConverter.toModel(kitchenService.save(kitchen));
    }

    @PutMapping("/{id}")
    public KitchenModel update(@PathVariable Long id, @RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen currentKitchen = kitchenService.findIfExists(id);
        kitchenInputDisconverter.copyToDomainObject(kitchenInput, currentKitchen);
        currentKitchen = kitchenService.save(currentKitchen);
        //BeanUtils.copyProperties(kitchen, currentKitchen, "id");
        return kitchenModelConverter.toModel(kitchenService.save(currentKitchen));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }

}
