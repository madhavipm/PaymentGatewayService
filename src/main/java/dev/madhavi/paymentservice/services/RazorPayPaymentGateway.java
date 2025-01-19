package dev.madhavi.paymentservice.services;

import com.razorpay.RazorpayException;
import dev.madhavi.paymentservice.services.PaymentService;
import org.json.JSONObject;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.springframework.stereotype.Service;

@Service
public class RazorPayPaymentGateway implements PaymentService {
    private RazorpayClient razorpayClient;

    public RazorPayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
        // Validate input
        if (orderId == null) {
            throw new IllegalArgumentException("OrderId cannot be null");
        }

        // Prepare the payment link request
        JSONObject paymentLinkRequest = new JSONObject();
        try {
            paymentLinkRequest.put("amount", 1000); // Amount in smallest currency unit
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", System.currentTimeMillis() + 1000 * 15 * 60); // Expiry in seconds
            paymentLinkRequest.put("reference_id", orderId.toString());
            paymentLinkRequest.put("description", "Payment for testing");

            // Customer details
            JSONObject customer = new JSONObject();
            customer.put("name", "Madhavi"); // Replace with dynamic data
            customer.put("contact", "1234567890"); // Replace with dynamic data
            customer.put("email", "abc1@gmail.com"); // Replace with dynamic data
            paymentLinkRequest.put("customer", customer);

            // Notification preferences
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("reminder_enable", true);
            paymentLinkRequest.put("callback_url", "https://github.com/madhavipm");
            paymentLinkRequest.put("callback_method", "get");

            // Additional notes (optional)
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima"); // Example note
            paymentLinkRequest.put("notes", notes);

        } catch (Exception e) {
            throw new RuntimeException("Error creating payment link request: " + e.getMessage(), e);
        }

        // Create the payment link using Razorpay API
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        return payment.get("short_url"); // Return the payment link details as a string
    }
}
