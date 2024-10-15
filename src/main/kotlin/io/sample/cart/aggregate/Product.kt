package io.sample.cart.aggregate

class Product(
    private val no: String,
    private val name: String,
    private val price: Int,
) {
    fun getNo(): String {
        return no
    }
}