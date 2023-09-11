package com.studies.foodorders.api.controllers.paymentway;

import com.studies.foodorders.api.converter.paymentway.PaymentWayModelConverter;
import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.services.paymentway.PaymentWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment-ways")
public class PaymentWayController {

    @Autowired
    private PaymentWayService paymentWayService;
    @Autowired
    private PaymentWayModelConverter paymentWayModelConverter;

    @GetMapping
    public ResponseEntity<List<PaymentWayModel>> list() {
        List<PaymentWayModel> paymentWays = paymentWayModelConverter.toCollectionModel(paymentWayService.list());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentWays);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentWayModel> find(@PathVariable Long id) {
        PaymentWayModel paymentWay = paymentWayModelConverter.toModel(paymentWayService.findIfExists(id));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentWay);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentWayModel save(@RequestBody @Valid PaymentWayInput paymentWayInput) {
        PaymentWay paymentWay = paymentWayModelConverter.toDomainObject(paymentWayInput);

        return paymentWayModelConverter.toModel(paymentWayService.save(paymentWay));
    }

    @PutMapping("/{id}")
    public PaymentWayModel atualizar(@PathVariable Long id,
                                         @RequestBody @Valid PaymentWayInput paymentWayInput) {
        PaymentWay currentPaymentWay = paymentWayService.findIfExists(id);

        paymentWayModelConverter.copyToDomainObject(paymentWayInput, currentPaymentWay);

        return paymentWayModelConverter.toModel(paymentWayService.save(currentPaymentWay));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentWayService.delete(id);
    }

}
