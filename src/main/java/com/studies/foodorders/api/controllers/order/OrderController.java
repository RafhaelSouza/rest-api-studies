package com.studies.foodorders.api.controllers.order;

import com.studies.foodorders.api.assemblers.order.OrderModelConverter;
import com.studies.foodorders.api.assemblers.order.OrderSummaryModelConverter;
import com.studies.foodorders.api.model.order.OrderInput;
import com.studies.foodorders.api.model.order.OrderModel;
import com.studies.foodorders.api.model.order.OrderSummaryModel;
import com.studies.foodorders.api.openapi.controllers.OrderControllerOpenApi;
import com.studies.foodorders.core.data.PageableCast;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.filter.OrderFilter;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.services.order.OrderService;
import com.studies.foodorders.infrastructure.repositories.restaurant.specifications.OrderSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderModelConverter orderModelConverter;

    @Autowired
    private OrderSummaryModelConverter orderSummaryModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<OrderSummaryModel> searchBy(@PageableDefault(size = 5) Pageable pageable, OrderFilter filters) {
        pageable = castPageable(pageable);

        Page<Order> ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(filters), pageable);

        List<OrderSummaryModel> ordersSummaryModel = orderSummaryModelConverter
                .toCollectionModel(ordersPage.getContent());

        return new PageImpl<>(ordersSummaryModel, pageable, ordersPage.getTotalElements());
    }

    @GetMapping(path = "/{orderCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderModel find(@PathVariable String orderCode) {
        return orderModelConverter.toModel(orderService.findIfExists(orderCode));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel add(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = orderModelConverter.toDomainObject(orderInput);

            newOrder.setClient(new User());
            newOrder.getClient().setId(1L);

            newOrder = orderService.makeOrder(newOrder);

            return orderModelConverter.toModel(newOrder);
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
