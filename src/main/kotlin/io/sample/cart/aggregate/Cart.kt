package io.sample.cart.aggregate

import io.sample.cart.command.AddItem
import io.sample.cart.command.ChangeQuantity
import io.sample.cart.command.RemoveItem
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

    fun addItem(command: AddItem) {
        val product = Product(no = command.productNo, name = command.productName, price = command.price)
        val item = Item(product = product, quantity = command.quantity)
        items.add(item)

        val event = ItemAdded(
            cartId = cartId,
            productNo = command.productNo,
            productName = command.productName,
            price = command.price,
            quantity = command.quantity
        )
        events.add(event)
    }

    fun changeQuantity(command: ChangeQuantity) {
        val foundItem = findItem(command.productNo)
        if (foundItem.isEmpty) {
            return
        }

        foundItem.get().setQuantity(command.quantity)

        val event = QuantityChanged(productNo = command.productNo, quantity = command.quantity)
        events.add(event)
    }

    fun removeItem(command: RemoveItem) {
        val foundItem = findItem(command.productNo)
        if (foundItem.isEmpty) {
            return
        }

        items.remove(foundItem.get())

        val event = ItemRemoved(productNo = command.productNo)
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