package io.sample.cart.service

import io.sample.cart.store.CartStore

class CartService(
    private val cartStore: CartStore,
) {

    fun addItem(cartId: String, productNo: String, productName: String, price: Int, quantity: Int) {
        val foundCart = cartStore.load(cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.addItem(productNo = productNo, productName = productName, price = price, quantity = quantity)

        cartStore.save(foundCart)
    }

    fun changeQuantity(cartId: String, productNo: String, quantity: Int) {
        val foundCart = cartStore.load(cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.changeQuantity(productNo = productNo, quantity = quantity)

        cartStore.save(foundCart)
    }

    fun removeItem(cartId: String, productNo: String) {
        val foundCart = cartStore.load(cartId) ?: throw IllegalArgumentException("Product not found")

        foundCart.removeItem(productNo = productNo)

        cartStore.save(foundCart)
    }
}