package com.studies.foodorders.domain.repositories.paymentway;

import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface PaymentWayRepository extends JpaRepository<PaymentWay, Long> {

    @Query("select max(updatedAt) from PaymentWay")
    OffsetDateTime getUpdateAt();

    @Query("select updatedAt from PaymentWay where id = :id")
    OffsetDateTime getUpdateAtById(Long id);

}
