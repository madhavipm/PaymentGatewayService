package dev.madhavi.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratePaymentLinkRequestDto {
    private String OrderId;

    public Long getOrderId() {
        return Long.parseLong(OrderId); // Convert to Long
    }

    public void setOrderId(String orderId) {
        this.OrderId = orderId;
    }
}
