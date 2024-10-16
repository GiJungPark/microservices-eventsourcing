package io.sample.cart.command

class RemoveItem(
    val cartId: String,

    val productNo: String,
) {
    fun validate() {
        require(cartId.isNotEmpty()) { "cartId can not be empty" }

        require(productNo.isNotEmpty()) { "productNo can not be empty" }
    }
}