package com.studies.foodorders.api.controllers.order;

import com.studies.foodorders.api.openapi.controllers.OrderFlowControllerOpenApi;
import com.studies.foodorders.domain.services.order.OrderFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
@RequiredArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {

    private final OrderFlowService orderFlowService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);
    }

    @PutMapping("/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);
    }

}
