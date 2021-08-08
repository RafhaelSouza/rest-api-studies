package com.studies.foodorders.domain.repositories.paymentway;

import com.studies.foodorders.domain.models.paymentway.PaymentWay;

import java.util.List;

public interface PaymentWayRepository {

    List<PaymentWay> list();
    PaymentWay find(Long id);
    PaymentWay save(PaymentWay kitchen);
    void delete(PaymentWay kitchen);

}
