package com.studies.foodorders.api.controllers.order;

import com.studies.foodorders.domain.services.order.OrderFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderId}")
@RequiredArgsConstructor
public class OrderFlowController {

    private final OrderFlowService orderFlowService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        orderFlowService.confirm(orderId);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long orderId) {
        orderFlowService.cancel(orderId);
    }

    @PutMapping("/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable Long orderId) {
        orderFlowService.deliver(orderId);
    }

}
