package com.studies.foodorders.api.controllers.order;

import com.studies.foodorders.api.converter.order.OrderModelConverter;
import com.studies.foodorders.api.converter.order.OrderSummaryModelConverter;
import com.studies.foodorders.api.model.order.OrderModel;
import com.studies.foodorders.api.model.order.OrderSummaryModel;
import com.studies.foodorders.domain.repositories.order.OrderRepository;
import com.studies.foodorders.domain.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public List<OrderSummaryModel> list() {
        return orderSummaryModelConverter.toCollectionModel(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public OrderModel find(@PathVariable Long orderId) {
        return orderModelConverter.toModel(orderService.findIfExists(orderId));
    }

}
