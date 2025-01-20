package dev.madhavi.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String generatePaymentLink(Long OrderId) throws  StripeException,RazorpayException;
}
