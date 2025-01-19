package dev.madhavi.paymentservice.controllers;

import com.razorpay.RazorpayException;
import dev.madhavi.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import dev.madhavi.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping()
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto requestDto) throws RazorpayException {
        //we should handle the exception in controller advice
        return paymentService.generatePaymentLink(requestDto.getOrderId());
    }
    @PostMapping("/webhook")
    public void webhook(@RequestBody Object webhook) throws RazorpayException {
        System.out.println("webhook triggred " );

    }
}
