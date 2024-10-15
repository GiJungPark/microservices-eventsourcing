package io.sample.cart.store.jpa

import io.sample.cart.aggregate.Cart
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "TB_CART")
class CartJpo {
    @Id
    private val id: String

    constructor(cart: Cart) {
        this.id = cart.getCartId();
    }

    constructor() {
        this.id = "";
    }
}