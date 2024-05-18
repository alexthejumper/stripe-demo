package com.example.demo

import com.stripe.exception.StripeException
import com.stripe.model.PaymentIntent
import com.stripe.param.PaymentIntentCreateParams
import org.springframework.stereotype.Service

@Service
class StripeService {

    fun createPaymentIntent(amount: Long, currency: String): PaymentIntent? {
        val params = PaymentIntentCreateParams.builder()
            .setAmount(amount)
            .setCurrency(currency)
            .build()

        return try {
            PaymentIntent.create(params)
        }
        catch (e: StripeException) {
            e.printStackTrace()
            null
        }
    }
}