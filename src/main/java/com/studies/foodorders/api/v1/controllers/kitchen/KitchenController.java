package com.studies.foodorders.api.v1.controllers.kitchen;

import com.studies.foodorders.api.v1.assemblers.kitchen.KitchenModelAssembler;
import com.studies.foodorders.api.v1.models.kitchen.KitchenInput;
import com.studies.foodorders.api.v1.models.kitchen.KitchenModel;
import com.studies.foodorders.api.v1.openapi.controllers.KitchenControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.services.kitchen.KitchenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/kitchens")
public class KitchenController implements KitchenControllerOpenApi {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelAssembler kitchenModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    /*@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenService.list());
    }*/

    @Deprecated
    @CheckSecurity.Kitchens.AllowToSearch
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<KitchenModel> list(@PageableDefault(size = 2) Pageable pageable) {

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        log.info("Listing kitchens with pages of {} records...", pageable.getPageSize());
        Page<Kitchen> kitchensPage = kitchenService.list(pageable);

        PagedModel<KitchenModel> kitchenPagedModel = pagedResourcesAssembler
                .toModel(kitchensPage, kitchenModelAssembler);;

        return kitchenPagedModel;
    }

    @Deprecated
    @CheckSecurity.Kitchens.AllowToSearch
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModel find(@PathVariable Long id) {
        return kitchenModelAssembler.toModel(kitchenService.findIfExists(id));
    }

    @Deprecated
    @CheckSecurity.Kitchens.AllowToUpdate
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModel save(@RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenModelAssembler.toDomainObject(kitchenInput);
        return kitchenModelAssembler.toModel(kitchenService.save(kitchen));
    }

    @Deprecated
    @CheckSecurity.Kitchens.AllowToUpdate
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KitchenModel update(@PathVariable Long id, @RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen currentKitchen = kitchenService.findIfExists(id);
        kitchenModelAssembler.copyToDomainObject(kitchenInput, currentKitchen);
        currentKitchen = kitchenService.save(currentKitchen);
        //BeanUtils.copyProperties(kitchen, currentKitchen, "id");
        return kitchenModelAssembler.toModel(kitchenService.save(currentKitchen));
    }

    @Deprecated
    @CheckSecurity.Kitchens.AllowToUpdate
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.delete(id);
    }

}
