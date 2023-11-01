package com.studies.foodorders.api.v1.assemblers.order;

import com.studies.foodorders.api.v1.controllers.order.OrderController;
import com.studies.foodorders.api.v1.links.*;
import com.studies.foodorders.api.v1.models.order.OrderInput;
import com.studies.foodorders.api.v1.models.order.OrderModel;
import com.studies.foodorders.core.security.ApiSecurity;
import com.studies.foodorders.domain.models.order.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderLinks orderLinks;

    @Autowired
    private RestaurantLinks restaurantLinks;

    @Autowired
    private UserLinks userLinks;

    @Autowired
    private PaymentWayLinks paymentWayLinks;

    @Autowired
    private CityLinks cityLinks;

    @Autowired
    private ProductLinks productLinks;

    @Autowired
    private ApiSecurity apiSecurity;

    public OrderModelAssembler() {
        super(OrderController.class, OrderModel.class);
    }

    public OrderModel toModel(Order order) {

        OrderModel orderModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderModel);

        if (apiSecurity.isAllowedToSearchOrders())
            orderModel.add(orderLinks.linkToOrders("orders"));

        if (apiSecurity.manageOrder(String.valueOf(order.getCode()))) {
            if (order.itCanBeConfirmed())
                orderModel.add(orderLinks.linkToOrderConfirmation(String.valueOf(order.getCode()), "confirm"));

            if (order.itCanBeCancelled())
                orderModel.add(orderLinks.linkToOrderCancellation(String.valueOf(order.getCode()), "cancel"));

            if (order.itCanBeDelivered())
                orderModel.add(orderLinks.linkToOrderDeliver(String.valueOf(order.getCode()), "deliver"));
        }

        if (apiSecurity.isAllowedToSearchRestaurants())
            orderModel.getRestaurant().add(restaurantLinks.linkToRestaurant(order.getRestaurant().getId()));

        if (apiSecurity.isAllowedToSearchUsersGroupsPermissions())
            orderModel.getClient().add(userLinks.linkToUser(order.getClient().getId()));

        if (apiSecurity.isAllowedToSearchPaymentWays())
            orderModel.getPaymentWay().add(paymentWayLinks.linkToPaymentWay(order.getPaymentWay().getId()));

        if (apiSecurity.isAllowedToSearchCities())
            orderModel.getDeliveryAddress().getCity().add(cityLinks.linkToCity(order.getDeliveryAddress().getCity().getId()));

        if (apiSecurity.isAllowedToSearchRestaurants()) {
            orderModel.getItems().forEach(item -> {
                item.add(productLinks.linkToProduct(orderModel.getRestaurant().getId(), item.getProductId(), "product"));
            });
        }

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
