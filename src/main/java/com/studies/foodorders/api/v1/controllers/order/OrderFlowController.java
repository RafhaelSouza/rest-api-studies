package com.studies.foodorders.api.v1.controllers.order;

import com.studies.foodorders.api.v1.openapi.controllers.OrderFlowControllerOpenApi;
import com.studies.foodorders.core.security.CheckSecurity;
import com.studies.foodorders.domain.services.order.OrderFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders/{orderCode}")
@RequiredArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {

    private final OrderFlowService orderFlowService;

    @CheckSecurity.Orders.AllowToManageOrders
    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Orders.AllowToManageOrders
    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Orders.AllowToManageOrders
    @PutMapping("/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);

        return ResponseEntity.noContent().build();
    }

}
