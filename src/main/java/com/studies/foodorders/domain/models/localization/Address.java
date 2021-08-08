package com.studies.foodorders.domain.models.localization;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Embeddable
public class Address {

    @Column(name = "address_postalcode")
    private String postalCode;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_district")
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;

}
