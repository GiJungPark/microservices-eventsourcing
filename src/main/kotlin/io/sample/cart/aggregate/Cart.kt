package io.sample.cart.aggregate

import io.sample.cart.event.Event
import io.sample.cart.event.ItemAdded
import io.sample.cart.event.ItemRemoved
import io.sample.cart.event.QuantityChanged
import java.util.*


class Cart(
    private val cartId: String,
    private val items: MutableList<Item> = mutableListOf(),
    private val events: MutableList<Event> = mutableListOf(),
) {

    fun addItem(productNo: String, productName: String, price: Int, quantity: Int) {
        val product = Product(no = productNo, name = productName, price = price)
        val item = Item(product = product, quantity = quantity)
        items.add(item)

        val event =
            ItemAdded(cartId, productNo = productNo, productName = productName, price = price, quantity = quantity)
        events.add(event)
    }

    fun changeQuantity(productNo: String, quantity: Int) {
        val foundItem = findItem(productNo)
        if (foundItem.isEmpty) {
            return
        }

        foundItem.get().setQuantity(quantity)

        val event = QuantityChanged(productNo = productNo, quantity = quantity)
        events.add(event)
    }

    fun removeItem(productNo: String) {
        val foundItem = findItem(productNo)
        if (foundItem.isEmpty) {
            return
        }

        items.remove(foundItem.get())

        val event = ItemRemoved(productNo = productNo)
        events.add(event)
    }

    fun getCartId(): String {
        return this.cartId
    }

    fun getEvents(): List<Event> {
        return this.events
    }

    private fun findItem(productNo: String): Optional<Item> {
        return items.stream().filter { item: Item -> productNo == item.getProduct().getNo() }.findFirst()
    }
}