package io.sample.cart.command

import io.sample.cart.exception.MinimumQuantityException

class ChangeQuantity(
    val cartId: String,

    val productNo: String,
    val quantity: Int,
) {
    fun validate() {
        require(cartId.isNotEmpty()) { "cartId can not be empty" }

        require(productNo.isNotEmpty()) { "productNo can not be empty" }

        if (quantity <= 0) {
            throw MinimumQuantityException()
        }
    }
}