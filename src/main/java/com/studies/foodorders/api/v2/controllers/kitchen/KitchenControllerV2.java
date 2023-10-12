package com.studies.foodorders.api.v2.controllers.kitchen;

import com.studies.foodorders.api.v2.assemblers.kitchen.KitchenModelAssemblerV2;
import com.studies.foodorders.api.v2.models.kitchen.KitchenInputV2;
import com.studies.foodorders.api.v2.models.kitchen.KitchenModelV2;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.services.kitchen.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v2/kitchens")
public class KitchenControllerV2 {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelAssemblerV2 kitchenModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<KitchenModelV2> list(@PageableDefault(size = 2) Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenService.list(pageable);

        PagedModel<KitchenModelV2> kitchenPagedModel = pagedResourcesAssembler
                .toModel(kitchensPage, kitchenModelAssembler);;

        return kitchenPagedModel;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModelV2 find(@PathVariable Long id) {
        return kitchenModelAssembler.toModel(kitchenService.findIfExists(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModelV2 save(@RequestBody @Valid KitchenInputV2 kitchenInput) {
        Kitchen kitchen = kitchenModelAssembler.toDomainObject(kitchenInput);
        return kitchenModelAssembler.toModel(kitchenService.save(kitchen));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModelV2 update(@PathVariable Long id, @RequestBody @Valid KitchenInputV2 kitchenInput) {
        Kitchen currentKitchen = kitchenService.findIfExists(id);
        kitchenModelAssembler.copyToDomainObject(kitchenInput, currentKitchen);
        currentKitchen = kitchenService.save(currentKitchen);

        return kitchenModelAssembler.toModel(kitchenService.save(currentKitchen));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }

}
