package io.sample.cart

class Item(
    private val product: Product,
    private var quantity: Int,
) {
    fun getProduct(): Product {
        return product
    }

    fun setQuantity(quantity: Int) {
        this.quantity = quantity
    }
}