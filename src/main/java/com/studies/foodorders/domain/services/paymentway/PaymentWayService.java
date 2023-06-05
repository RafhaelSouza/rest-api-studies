package com.studies.foodorders.domain.services.paymentway;

import com.studies.foodorders.domain.exceptions.PaymentWayNotFoundException;
import com.studies.foodorders.domain.exceptions.UsedEntityException;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.repositories.paymentway.PaymentWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentWayService {

    public static final String PAYMENT_WAY_IN_USE = "Payment Way in use.";

    @Autowired
    private PaymentWayRepository paymentWayRepository;

    public List<PaymentWay> list() {
        return paymentWayRepository.findAll();
    }

    public Optional<PaymentWay> find(Long id) {
        Optional<PaymentWay> paymentWay = paymentWayRepository.findById(id);
        return paymentWay;
    }

    @Transactional
    public PaymentWay save(PaymentWay paymentWay) {
        return paymentWayRepository.save(paymentWay);
    }

    @Transactional
    public void delete(Long formaPagamentoId) {
        try {
            paymentWayRepository.deleteById(formaPagamentoId);
            paymentWayRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PaymentWayNotFoundException(formaPagamentoId);

        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format(PAYMENT_WAY_IN_USE, formaPagamentoId));
        }
    }

    public PaymentWay findIfExists(Long id) {
        return paymentWayRepository.findById(id)
                .orElseThrow(() -> new PaymentWayNotFoundException(id));
    }

}
