package com.studies.foodorders.domain.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.foodorders.domain.events.ConfirmedOrderEvent;
import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.models.localization.Address;
import com.studies.foodorders.domain.models.order.enumerations.OrderStatus;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.models.security.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tab_orders")
public class Order extends AbstractAggregateRoot<Order> implements Serializable {

    private static final long serialVersionUID = 6122106143932613290L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(nullable = false)
    private UUID code;

    @Column(nullable = false)
    private BigDecimal partialPrice;

    @Column(nullable = false)
    private BigDecimal shippingCosts;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;

    @JsonIgnore
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime confirmedAt;

    @JsonIgnore
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime canceledAt;

    @JsonIgnore
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime deliveredAt;

    @Embedded
    private Address deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentway_id", nullable = false)
    private PaymentWay paymentWay;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_user_id", nullable = false)
    private User client;

    public void calculateTotalPrice() {
        getItems().forEach(OrderItem::calculateTotalPrice);

        this.partialPrice = getItems().stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalPrice = this.partialPrice.add(this.shippingCosts);
    }

    public void calculateShippingCosts() {
        setShippingCosts(getRestaurant().getShippingCosts());
    }

    public void assignOrderToItems() {
        getItems().forEach(item -> item.setOrder(this));
    }

    public void confirm() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmedAt(OffsetDateTime.now());

        registerEvent(new ConfirmedOrderEvent(this));
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELLED);
        setCanceledAt(OffsetDateTime.now());
    }

    public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
    }

    private void setStatus(OrderStatus newStatus) {
        if (getStatus().itCanNotChangeTo(newStatus)) {
            throw new BusinessException(
                    String.format("Order status %s cannot be changed from %s to %s",
                            getCode().toString(), getStatus().getDescription(),
                            newStatus.getDescription()));
        }

        this.status = newStatus;
    }

    @PrePersist
    private void codeGenerate() {
        setCode(UUID.randomUUID());
    }

}
