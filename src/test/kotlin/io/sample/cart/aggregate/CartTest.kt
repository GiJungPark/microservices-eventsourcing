package io.sample.cart.aggregate

import io.sample.cart.command.AddItem
import io.sample.cart.command.ChangeQuantity
import io.sample.cart.command.RemoveItem
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class CartTest {

    private val CART_ID: String = "1234"

    @DisplayName("카트에 아이템을 추가할 수 있다.")
    @Test
    fun addItem() {
        // given
        val cart = Cart(CART_ID)
        val command = AddItem(cartId = CART_ID, productNo = "P1", productName = "product", price = 10_000, quantity = 1)

        // when
        cart.addItem(command)

        // then
        assertEquals(1, cart.getItems().size)
    }

    @DisplayName("카트에 담긴 아이템을 삭제할 수 있다.")
    @Test
    fun removeItem() {
        // given
        val cart = Cart(CART_ID)
        val addItemCommand = AddItem(cartId = CART_ID, productNo = "P1", productName = "product", price = 10_000, quantity = 1)

        cart.addItem(addItemCommand)

        // when
        val removeItemCommand = RemoveItem(cartId = CART_ID, productNo = "P1")
        cart.removeItem(removeItemCommand)

        // then
        assertEquals(0, cart.getItems().size)
    }
}