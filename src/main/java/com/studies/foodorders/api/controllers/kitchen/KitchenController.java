package com.studies.foodorders.api.controllers.kitchen;

import com.studies.foodorders.api.converter.kitchen.KitchenModelConverter;
import com.studies.foodorders.api.model.kitchen.KitchenInput;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.openapi.controllers.KitchenControllerOpenApi;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.services.kitchen.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController implements KitchenControllerOpenApi {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelConverter kitchenModelConverter;

    /*@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenService.list());
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<KitchenModel> list(@PageableDefault(size = 2) Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenService.list(pageable);
        List<KitchenModel> kitchensModel = kitchenModelConverter.toCollectionModel(kitchensPage.getContent());

        return new PageImpl<>(kitchensModel, pageable, kitchensPage.getTotalElements());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModel find(@PathVariable Long id) {
        return kitchenModelConverter.toModel(kitchenService.findIfExists(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModel save(@RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenModelConverter.toDomainObject(kitchenInput);
        return kitchenModelConverter.toModel(kitchenService.save(kitchen));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModel update(@PathVariable Long id, @RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen currentKitchen = kitchenService.findIfExists(id);
        kitchenModelConverter.copyToDomainObject(kitchenInput, currentKitchen);
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
