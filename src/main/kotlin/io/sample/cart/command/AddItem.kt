package io.sample.cart.command

data class AddItem(
    val cartId: String,

    val productNo: String,
    val productName: String,
    val price: Int,
    val quantity: Int,
)