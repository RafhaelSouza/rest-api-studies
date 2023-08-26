package com.studies.foodorders.domain.models.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_product_photo")
public class ProductPhoto implements Serializable {

    private static final long serialVersionUID = -8508611142308753900L;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Long id;

    private String fileName;
    private String description;
    private String contentType;
    private Long fileSize;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    public Long getRestaurantId() {
        if (getProduct() == null)
            return null;

        return getProduct().getRestaurant().getId();
    }

}
