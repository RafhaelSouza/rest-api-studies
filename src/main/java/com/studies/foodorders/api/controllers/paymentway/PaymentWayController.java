package com.studies.foodorders.api.controllers.paymentway;

import com.studies.foodorders.api.assemblers.paymentway.PaymentWayModelConverter;
import com.studies.foodorders.api.model.paymentway.PaymentWayInput;
import com.studies.foodorders.api.model.paymentway.PaymentWayModel;
import com.studies.foodorders.api.openapi.controllers.PaymentWayControllerOpenApi;
import com.studies.foodorders.domain.models.paymentway.PaymentWay;
import com.studies.foodorders.domain.services.paymentway.PaymentWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment-ways")
public class PaymentWayController implements PaymentWayControllerOpenApi {

    @Autowired
    private PaymentWayService paymentWayService;
    @Autowired
    private PaymentWayModelConverter paymentWayModelConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentWayModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime paymentWayUpdateAt = paymentWayService.getUpdateAt();

        if (paymentWayUpdateAt != null)
            eTag = String.valueOf(paymentWayUpdateAt.toEpochSecond());

        if (request.checkNotModified(eTag))
            return null;

        List<PaymentWayModel> paymentWays = paymentWayModelConverter.toCollectionModel(paymentWayService.list());

        return ResponseEntity.ok()
                // Only browser can make cache
                //.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())
                // Any server can make cache and it is default
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                // It's checked all cached information with the responses
                //.cacheControl(CacheControl.noCache())
                // No cache is made (It's possible add a request header called Cache-Control: no-cache)
                //.cacheControl(CacheControl.noStore())
                .eTag(eTag)
                .body(paymentWays);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentWayModel> find(@PathVariable Long id, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime paymentWayUpdateAt = paymentWayService.getUpdateAtById(id);

        if (paymentWayUpdateAt != null)
            eTag = String.valueOf(paymentWayUpdateAt.toEpochSecond());

        if (request.checkNotModified(eTag))
            return null;

        PaymentWayModel paymentWay = paymentWayModelConverter.toModel(paymentWayService.findIfExists(id));

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentWay);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentWayModel save(@RequestBody @Valid PaymentWayInput paymentWayInput) {
        PaymentWay paymentWay = paymentWayModelConverter.toDomainObject(paymentWayInput);

        return paymentWayModelConverter.toModel(paymentWayService.save(paymentWay));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentWayModel update(@PathVariable Long id,
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
