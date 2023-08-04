package com.studies.foodorders.domain.models.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_products")
public class Product implements Serializable {

    private static final long serialVersionUID = 4456691667250783561L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "restaurant_id")
    private Restaurant restaurant;

}
