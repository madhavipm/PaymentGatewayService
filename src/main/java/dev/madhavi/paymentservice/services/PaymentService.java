package dev.madhavi.paymentservice.services;

import com.razorpay.RazorpayException;

public interface PaymentService {
    public String generatePaymentLink(Long OrderId) throws RazorpayException;
}
