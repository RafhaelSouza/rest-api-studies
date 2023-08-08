package com.studies.foodorders.domain.models.order.enumerations;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELLED("Cancelled", CREATED);

    private String description;
    private List<OrderStatus> previousOrderStatus;

    OrderStatus(String description, OrderStatus... previousOrderStatus) {
        this.description = description;
        this.previousOrderStatus = Arrays.asList(previousOrderStatus);
    }

    public String getDescription() {
        return description;
    }

    public boolean itCanNotChangeTo(OrderStatus newStatus) {
        return !newStatus.previousOrderStatus.contains(this);
    }

}
