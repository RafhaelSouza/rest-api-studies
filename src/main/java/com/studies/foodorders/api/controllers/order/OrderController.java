package com.studies.foodorders.api.controllers.order;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.studies.foodorders.api.converter.order.OrderModelConverter;
import com.studies.foodorders.api.converter.order.OrderSummaryModelConverter;
import com.studies.foodorders.api.model.order.OrderInput;
import com.studies.foodorders.api.model.order.OrderModel;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.models.order.Order;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.services.order.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderModelConverter orderModelConverter;

    @Autowired
    private OrderSummaryModelConverter orderSummaryModelConverter;

    /*@GetMapping
    public List<OrderSummaryModel> list() {
        return orderSummaryModelConverter.toCollectionModel(orderRepository.findAll());
    }*/

    @GetMapping
    public MappingJacksonValue list(@RequestParam(required = false) String fields) {

        MappingJacksonValue ordersWrapper = new MappingJacksonValue(orderSummaryModelConverter.toCollectionModel(orderRepository.findAll()));

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(fields)) {
            filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
        }

        ordersWrapper.setFilters(filterProvider);

        return ordersWrapper;
    }

    @GetMapping("/{orderCode}")
    public OrderModel find(@PathVariable String orderCode) {
        return orderModelConverter.toModel(orderService.findIfExists(orderCode));
    }

    @PostMapping
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

}
