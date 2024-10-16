package io.sample.cart.aggregate

import io.sample.cart.command.AddItem
import io.sample.cart.command.ChangeQuantity
import io.sample.cart.command.CreateCart
import io.sample.cart.command.RemoveItem
import io.sample.cart.event.*
import java.lang.reflect.InvocationTargetException
import java.util.*


class Cart(command: CreateCart) {

    private lateinit var cartId: String
    private var deleted: Boolean = false
    private var items: MutableList<Item> = mutableListOf()
    private var events: MutableList<Event> = mutableListOf()

    init {
        apply(CartCreated(cartId = command.cartId))
    }

    private fun on(event: CartCreated) {
        cartId = event.cartId
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

    fun removeCart() {
        apply(CartRemoved(cartId))
    }

    private fun on(event: CartRemoved) {
        markDelete()
    }

    fun apply(event: Event) {
        apply(event, true);
    }

    fun apply(event: Event, isNew: Boolean) {
        try {
            val handler = javaClass.getDeclaredMethod("on", event.javaClass)

            if (handler != null) {
                handler.setAccessible(true)
                handler.invoke(this, event)
                if (isNew) {
                    events.add(event)
                }
            }
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            // TODO: 예외 처리
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            // TODO: 예외 처리
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
            // TODO: 예외 처리
        }
    }

    private fun markDelete() {
        deleted = true
    }

    fun getCartId(): String {
        return this.cartId
    }

    fun getEvents(): List<Event> {
        return this.events
    }

    fun getItems(): List<Item> {
        return this.items
    }

    private fun findItem(productNo: String): Optional<Item> {
        return items.stream().filter { item: Item -> productNo == item.getProduct().getNo() }.findFirst()
    }
}