package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/payment")
    fun getPaymentPage(): String {
        return "payment"
    }

    @GetMapping("/success")
    fun getSuccessPage(): String {
        return "success"
    }

    @GetMapping("/cancel")
    fun getCancelPage(): String {
        return "cancel"
    }


}