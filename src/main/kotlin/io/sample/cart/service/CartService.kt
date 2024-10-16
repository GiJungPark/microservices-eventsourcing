package io.sample.cart.service

import io.sample.cart.aggregate.Cart
import io.sample.cart.command.*
import io.sample.cart.store.CartStore

class CartService(
    private val cartStore: CartStore,
) {
    fun createCart(command: CreateCart) {
        if (cartStore.exists(command.cartId)) {
            throw IllegalArgumentException("CartId already exists")
        }

        val cart = Cart(command)
        cartStore.save(cart)
    }

    fun addItem(command: AddItem) {
        command.validate()

        val foundCart = cartStore.load(command.cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.addItem(command)

        cartStore.save(foundCart)
    }

    fun changeQuantity(command: ChangeQuantity) {
        command.validate()

        val foundCart = cartStore.load(command.cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.changeQuantity(command)

        cartStore.save(foundCart)
    }

    fun removeItem(command: RemoveItem) {
        command.validate()

        val foundCart = cartStore.load(command.cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.removeItem(command)

        cartStore.save(foundCart)
    }

    fun removeCart(command: RemoveCart) {
        val foundCart = cartStore.load(command.cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.removeCart()

        cartStore.save(foundCart)
    }
}