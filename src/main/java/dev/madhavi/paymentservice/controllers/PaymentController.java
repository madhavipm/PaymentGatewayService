package dev.madhavi.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.madhavi.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import dev.madhavi.paymentservice.services.PaymentGateway;
import dev.madhavi.paymentservice.services.RazorPayPaymentGateway;
import dev.madhavi.paymentservice.services.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentGateway paymentGateway;
    public PaymentController(@Qualifier("stripePaymentGateway") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    @PostMapping()
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto requestDto) throws StripeException,RazorpayException {
        //we should handle the exception in controller advice
        return paymentGateway.generatePaymentLink(requestDto.getOrderId());
    }
    @PostMapping("/webhook")
    public void webhook(@RequestBody Object webhook) throws RazorpayException {
        System.out.println("webhook triggred " );

    }
}
