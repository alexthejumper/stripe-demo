package com.example.demo

import com.stripe.model.checkout.Session
import com.stripe.param.checkout.SessionCreateParams
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api/stripe")
class StripeController(private val stripeService: StripeService) {

    @PostMapping("/create-checkout-session")
    @ResponseBody
    fun createCheckoutSession(): Map<String, String> {
        val params = SessionCreateParams.builder()
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("usd")
                            .setUnitAmount(2000L)
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("Test Product")
                                    .build()
                            )
                            .build()
                    )
                    .setQuantity(1L)
                    .build()
            )
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("usd")
                            .setUnitAmount(5000L)
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("Test Product 2")
                                    .build()
                            )
                            .build()
                    )
                    .setQuantity(1L)
                    .build()
            )
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:8080/success")
            .setCancelUrl("http://localhost:8080/cancel")
            .build()

        val session: Session = Session.create(params)

        return mapOf("id" to session.id)
    }
}