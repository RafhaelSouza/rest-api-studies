package com.studies.foodorders.domain.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.foodorders.domain.models.localization.Address;
import com.studies.foodorders.domain.models.order.enumerations.OrderStatus;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.models.security.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 6122106143932613290L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal partialPrice;

    @Column(nullable = false)
    private BigDecimal shippingCosts;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(columnDefinition = "timestamp")
    private LocalDateTime confirmedAt;

    @JsonIgnore
    @Column(columnDefinition = "timestamp")
    private LocalDateTime deliveredAt;

    @JsonIgnore
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentway_id", nullable = false)
    private PaymentWay paymentMethod;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
