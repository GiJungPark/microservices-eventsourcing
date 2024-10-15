package io.sample.cart

class Cart(
    private val cartId: String,
    private val items: MutableList<Item> = mutableListOf(),
) {
    fun addItem(productNo: String, productName: String, quantity: Int) {
        // TODO
    }

    fun changeQuantity(productNo: String, quantity: Int) {
        // TODO
    }

    fun removeItem(productNo: String) {
        // TODO
    }
}