package com.studies.foodorders.api.v1.controllers.order;

import com.studies.foodorders.api.v1.assemblers.order.OrderModelAssembler;
import com.studies.foodorders.api.v1.assemblers.order.OrderSummaryModelAssembler;
import com.studies.foodorders.api.v1.models.order.OrderInput;
import com.studies.foodorders.api.v1.models.order.OrderModel;
import com.studies.foodorders.api.v1.models.order.OrderSummaryModel;
import com.studies.foodorders.api.v1.openapi.controllers.OrderControllerOpenApi;
import com.studies.foodorders.core.data.PageWrapper;
import com.studies.foodorders.core.data.PageableCast;
import com.studies.foodorders.core.security.ApiSecurity;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.filter.OrderFilter;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.security.Users;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.services.order.OrderService;
import com.studies.foodorders.infrastructure.repositories.restaurant.specifications.OrderSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderModelAssembler orderModelAssembler;

    @Autowired
    private OrderSummaryModelAssembler orderSummaryModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @Autowired
    private ApiSecurity apiSecurity;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<OrderSummaryModel> searchBy(@PageableDefault(size = 5) Pageable pageable, OrderFilter filters) {
        Pageable castPageable = castPageable(pageable);

        Page<Order> ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(filters), castPageable);

        ordersPage = new PageWrapper<>(ordersPage, pageable);

        return pagedResourcesAssembler.toModel(ordersPage, orderSummaryModelAssembler);
    }

    @GetMapping(path = "/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel find(@PathVariable String orderCode) {
        return orderModelAssembler.toModel(orderService.findIfExists(orderCode));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel add(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = orderModelAssembler.toDomainObject(orderInput);

            newOrder.setClient(new Users());
            newOrder.getClient().setId(apiSecurity.getUserId());

            newOrder = orderService.makeOrder(newOrder);

            return orderModelAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private Pageable castPageable(Pageable apiPageable) {

        var mapping = Map.of(
                "code", "code",
                "restaurant.name", "restaurant.name",
                "clientName", "client.name",
                "totalPrice", "totalPrice"
        );

        return PageableCast.translate(apiPageable, mapping);
    }

}
