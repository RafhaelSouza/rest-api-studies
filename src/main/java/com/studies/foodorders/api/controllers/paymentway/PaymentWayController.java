package com.studies.foodorders.api.controllers.paymentway;

import com.studies.foodorders.api.converter.paymentway.PaymentWayModelConverter;
import com.studies.foodorders.api.converter.paymentway.PaymentWayModelDisconverter;
import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.services.paymentway.PaymentWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment-ways")
public class PaymentWayController {

    @Autowired
    private PaymentWayService paymentWayService;
    @Autowired
    private PaymentWayModelConverter paymentWayModelConverter;

    @Autowired
    private PaymentWayModelDisconverter paymentWayModelDisconverter;

    @GetMapping
    public List<PaymentWayModel> list() {
        return paymentWayModelConverter.toCollectionModel(paymentWayService.list());
    }

    @GetMapping("/id}")
    public PaymentWayModel find(@PathVariable Long id) {
        return paymentWayModelConverter.toModel(paymentWayService.findIfExists(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentWayModel save(@RequestBody @Valid PaymentWayInput paymentWayInput) {
        PaymentWay paymentWay = paymentWayModelDisconverter.toDomainObject(paymentWayInput);

        return paymentWayModelConverter.toModel(paymentWayService.save(paymentWay));
    }

    @PutMapping("/{id}")
    public PaymentWayModel atualizar(@PathVariable Long id,
                                         @RequestBody @Valid PaymentWayInput paymentWayInput) {
        PaymentWay currentPaymentWay = paymentWayService.findIfExists(id);

        paymentWayModelDisconverter.copyToDomainObject(paymentWayInput, currentPaymentWay);

        return paymentWayModelConverter.toModel(paymentWayService.save(currentPaymentWay));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentWayService.delete(id);
    }

}
