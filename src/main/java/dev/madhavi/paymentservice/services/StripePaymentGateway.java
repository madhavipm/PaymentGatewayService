package dev.madhavi.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripePaymentGateway")
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.api.key}")
    private String stripeApiKey;
    @Override
    public String generatePaymentLink(Long OrderId) throws StripeException {
    //make a call to stripe to generate payment link
        Stripe.apiKey = stripeApiKey;

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(5000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Stripe for testing by Madhavi").build()
                        )
                        .build();

        Price price = Price.create(priceParams);
        PaymentLinkCreateParams paymentParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentParams);
        return paymentLink.toString();
    }

}
