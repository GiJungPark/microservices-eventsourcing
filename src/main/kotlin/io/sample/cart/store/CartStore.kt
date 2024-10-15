package io.sample.cart.store

import io.sample.cart.aggregate.Cart

class CartStore {

    fun save(cart: Cart) {
        // TODO
    }

    fun load(cartId: String): Cart? {
        // TODO
        return Cart("", mutableListOf(), mutableListOf())
    }
}