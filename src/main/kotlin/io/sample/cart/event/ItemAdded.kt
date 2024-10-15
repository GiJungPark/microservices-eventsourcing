package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ItemAdded : Event {
    private val productNo: String
    private val productName: String
    private val price: Int
    private val quantity: Int

    constructor(cartId: String, productNo: String, productName: String, price: Int, quantity: Int) {
        super.setCartId(cartId)
        this.productNo = productNo
        this.productName = productName
        this.price = price
        this.quantity = quantity
    }
}