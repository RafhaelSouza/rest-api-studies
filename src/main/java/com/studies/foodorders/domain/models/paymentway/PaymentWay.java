package com.studies.foodorders.domain.models.paymentway;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tab_payment_ways")
public class PaymentWay implements Serializable {

    private static final long serialVersionUID = -397760876486259867L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @UpdateTimestamp
    @Column(columnDefinition = "timestamp")
    private OffsetDateTime updatedAt;

}
