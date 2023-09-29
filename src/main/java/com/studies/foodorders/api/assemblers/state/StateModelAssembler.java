package com.studies.foodorders.api.assemblers.state;

import com.studies.foodorders.api.controllers.localization.StateController;
import com.studies.foodorders.api.model.localization.state.StateInput;
import com.studies.foodorders.api.model.localization.state.StateModel;
import com.studies.foodorders.domain.models.localization.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> {

    @Autowired
    private ModelMapper modelMapper;

    public StateModelAssembler() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state) {

        StateModel stateModel = createModelWithId(state.getId(), state);
        modelMapper.map(state, stateModel);

        stateModel.add(linkTo(StateController.class).withRel("states"));

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(StateController.class).withSelfRel());
    }

    public State toDomainObject(StateInput stateInput) {
        return modelMapper.map(stateInput, State.class);
    }

    public void copyToDomainObject(StateInput stateInput, State state) {
        modelMapper.map(stateInput, state);
    }

}
