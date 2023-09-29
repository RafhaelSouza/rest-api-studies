package com.studies.foodorders.api.controllers.localization;

import com.studies.foodorders.api.assemblers.state.StateModelAssembler;
import com.studies.foodorders.api.model.localization.state.StateInput;
import com.studies.foodorders.api.model.localization.state.StateModel;
import com.studies.foodorders.api.openapi.controllers.StateControllerOpenApi;
import com.studies.foodorders.domain.models.localization.State;
import com.studies.foodorders.domain.services.localization.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/states")
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateService stateService;

    @Autowired
    private StateModelAssembler stateModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<StateModel> list() {
        return stateModelAssembler.toCollectionModel(stateService.list());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StateModel find(@PathVariable Long id) {
        return stateModelAssembler.toModel(stateService.findIfExists(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel save(@RequestBody @Valid StateInput stateInput) {
        State state = stateModelAssembler.toDomainObject(stateInput);
        return stateModelAssembler.toModel(stateService.save(state));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StateModel update(@PathVariable Long id, @RequestBody @Valid StateInput stateInput) {
        State currentState = stateService.findIfExists(id);
        stateModelAssembler.copyToDomainObject(stateInput, currentState);
        currentState = stateService.save(currentState);
        return stateModelAssembler.toModel(stateService.save(currentState));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateService.delete(id);
    }
}
