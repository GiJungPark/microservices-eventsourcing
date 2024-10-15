package io.sample.cart

import io.sample.cart.event.ItemAdded
import io.sample.cart.event.ItemRemoved
import io.sample.cart.event.QuantityChanged

class Cart(
    private val cartId: String,
    private val items: MutableList<Item> = mutableListOf(),
    private val events: MutableList<Any> = mutableListOf(),
) {
    fun addItem(itemAdded: ItemAdded) {
        // TODO
    }

    fun changeQuantity(quantityChanged: QuantityChanged) {
        // TODO
    }

    fun removeItem(itemRemoved: ItemRemoved) {
        // TODO
    }
}