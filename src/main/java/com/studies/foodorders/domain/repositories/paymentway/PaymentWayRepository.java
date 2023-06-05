package com.studies.foodorders.domain.repositories.paymentway;

import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentWayRepository extends JpaRepository<PaymentWay, Long> {}
