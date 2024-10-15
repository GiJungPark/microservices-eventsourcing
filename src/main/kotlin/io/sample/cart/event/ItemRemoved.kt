package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ItemRemoved : Event {
    private val productNo: String

    constructor(productNo: String) {
        this.productNo = productNo
    }
}