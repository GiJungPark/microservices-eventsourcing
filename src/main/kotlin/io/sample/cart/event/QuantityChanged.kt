package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class QuantityChanged : Event {
    private val productNo: String
    private val quantity: Int

    constructor(productNo: String, quantity: Int) {
        this.productNo = productNo
        this.quantity = quantity
    }
}