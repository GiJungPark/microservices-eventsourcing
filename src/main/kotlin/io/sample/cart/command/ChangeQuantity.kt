package io.sample.cart.command

class ChangeQuantity(
    val cartId: String,

    val productNo: String,
    val quantity: Int,
)