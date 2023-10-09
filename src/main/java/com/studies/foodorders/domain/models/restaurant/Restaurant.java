package com.studies.foodorders.domain.models.restaurant;

import com.studies.foodorders.core.validation.DeliveryTax;
import com.studies.foodorders.core.validation.Groups;
import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.localization.Address;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.models.product.Product;
import com.studies.foodorders.domain.models.security.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_restaurants")
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 2472864608652103799L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(nullable = false)
    private String name;

    @DeliveryTax
    @Column(nullable = false)
    private BigDecimal shippingCosts;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;

    private Boolean active = Boolean.TRUE;
    private Boolean opened = Boolean.TRUE;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @ManyToMany
    @JoinTable(name = "restaurant_payment_way",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "paymentway_id"))
    private Set<PaymentWay> paymentWay = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurant_responsible_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private Set<User> responsible = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    public void activate() {
        setActive(true);
    }

    public void inactivate() {
        setActive(false);
    }

    public void toOpen() {
        setOpened(true);
    }

    public void toClose() {
        setOpened(false);
    }

    public boolean isOpened() {
        return this.opened;
    }

    public boolean isClose() {
        return !isOpened();
    }

    public boolean isInactive() {
        return !isActive();
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean openingAllowed() {
        return isActive() && isClose();
    }

    public boolean activationAllowed() {
        return isInactive();
    }

    public boolean inactivationAllowed() {
        return isActive();
    }

    public boolean closingAllowed() {
        return isOpened();
    }

    public boolean addPaymentWay(PaymentWay paymentWay) {
        return getPaymentWay().add(paymentWay);
    }

    public boolean deletePaymentWay(PaymentWay paymentWay) {
        return getPaymentWay().remove(paymentWay);
    }

    public boolean addResponsible(User user) {
        return getResponsible().add(user);
    }

    public boolean deleteResponsible(User user) {
        return getResponsible().remove(user);
    }

    public boolean acceptsPaymentWay(PaymentWay paymentWay) {
        return getPaymentWay().contains(paymentWay);
    }

    public boolean doesNotAcceptPaymentWay(PaymentWay paymentWay) {
        return !acceptsPaymentWay(paymentWay);
    }

}
