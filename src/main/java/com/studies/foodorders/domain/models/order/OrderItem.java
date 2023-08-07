package com.studies.foodorders.domain.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.foodorders.domain.models.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_order_items")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1838884121786447306L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String observations;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public void calculateTotalPrice() {
        BigDecimal unitPrice = this.getUnitPrice();
        Integer amount = this.getAmount();

        if (unitPrice == null) {
            unitPrice = BigDecimal.ZERO;
        }

        if (amount == null) {
            amount = 0;
        }

        this.setTotalPrice(unitPrice.multiply(new BigDecimal(amount)));
    }

}
