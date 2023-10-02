package com.studies.foodorders.api.assemblers.order;

import com.studies.foodorders.api.controllers.localization.CityController;
import com.studies.foodorders.api.controllers.order.OrderController;
import com.studies.foodorders.api.controllers.paymentway.PaymentWayController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantController;
import com.studies.foodorders.api.controllers.restaurant.RestaurantProductController;
import com.studies.foodorders.api.controllers.security.UserController;
import com.studies.foodorders.api.links.OrderLinks;
import com.studies.foodorders.api.model.order.OrderInput;
import com.studies.foodorders.api.model.order.OrderModel;
import com.studies.foodorders.domain.models.order.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderLinks orderLinks;

    public OrderModelAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    public OrderModel toModel(Order order) {

        OrderModel orderModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(orderLinks.linkToOrders());

        orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
                .find(order.getRestaurant().getId())).withSelfRel());

        orderModel.getClient().add(linkTo(methodOn(UserController.class)
                .find(order.getClient().getId())).withSelfRel());

        orderModel.getPaymentWay().add(linkTo(methodOn(PaymentWayController.class)
                .find(order.getPaymentWay().getId(), null)).withSelfRel());

        orderModel.getDeliveryAddress().getCity().add(linkTo(methodOn(CityController.class)
                .find(order.getDeliveryAddress().getCity().getId())).withSelfRel());

        orderModel.getItems().forEach(item -> {
            item.add(linkTo(methodOn(RestaurantProductController.class)
                    .find(orderModel.getRestaurant().getId(), item.getProductId()))
                    .withRel("product"));
        });

        return orderModel;
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
