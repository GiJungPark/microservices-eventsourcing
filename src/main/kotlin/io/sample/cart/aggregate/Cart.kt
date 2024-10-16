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
    private var items: MutableList<Item> = mutableListOf(),
    internal var events: MutableList<Event> = mutableListOf(),
) {

    fun apply(event: Event) {
        apply(event, true);
    }

    fun apply(event: Event, isNew: Boolean) {
        val handler = javaClass.getDeclaredMethod("on", event.javaClass)

        if (handler != null) {
            handler.setAccessible(true)
            handler.invoke(this, event)
            if (isNew) {
                events.add(event)
            }
        }
    }

    fun addItem(command: AddItem) {
        if (!containsItem(command.productNo)) {
            val event = ItemAdded(
                cartId = command.cartId,
                productNo = command.productNo,
                productName = command.productName,
                price = command.price,
                quantity = command.quantity
            )

            apply(event)
        }
    }

    private fun on(event: ItemAdded) {
        val product = Product(no = event.productNo, name = event.productName, price = event.price)
        val item = Item(product = product, quantity = event.quantity)
        items.add(item)
    }

    private fun containsItem(productNo: String): Boolean {
        return !this.items.stream().filter { item -> productNo == item.getProduct().getNo() }
            .findFirst().isEmpty
    }

    fun changeQuantity(command: ChangeQuantity) {
        val foundItem = findItem(command.productNo)
        if (foundItem.isEmpty) {
            return
        }

        val event = QuantityChanged(cartId = cartId, productNo = command.productNo, quantity = command.quantity)

        apply(event)
    }

    private fun on(event: QuantityChanged) {
        val foundItem = findItem(event.productNo)

        if (foundItem.isPresent) {
            foundItem.get().setQuantity(event.quantity)
        }
    }

    fun removeItem(command: RemoveItem) {
        val foundItem = findItem(command.productNo)
        if (foundItem.isEmpty) {
            return
        }

        val event = ItemRemoved(cartId = cartId, productNo = command.productNo)
        apply(event)
    }

    private fun on(event: ItemRemoved) {
        val foundItem = findItem(event.productNo)

        if (foundItem.isPresent) {
            items.remove(foundItem.get())
        }
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