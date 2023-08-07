package com.studies.foodorders.api.converter.order;

import com.studies.foodorders.api.model.order.OrderInput;
import com.studies.foodorders.api.model.order.OrderModel;
import com.studies.foodorders.domain.models.order.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelConverter {

    @Autowired
    private ModelMapper modelMapper;

    public OrderModel toModel(Order order) {
        return modelMapper.map(order, OrderModel.class);
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }

    public Order toDomainObject(OrderInput orderInput) {
        return modelMapper.map(orderInput, Order.class);
    }

    public void copyToDomainObject(OrderInput orderInput, Order order) {
        modelMapper.map(orderInput, order);
    }

}
