package io.sample.cart.store.jpa

import io.sample.cart.aggregate.Cart
import io.sample.cart.event.Event
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "TB_CART_EVENT")
class CartEventJpo {
    @Id
    private val eventId: String
    private val cartId: String
    @Lob
    private val payload: String
    private val time: Long

    constructor(cart: Cart, event: Event) {
        eventId = event.getEventId()
        cartId = cart.getCartId()
        payload = event.getPayLoad()
        time = event.getTime()
    }

    constructor() {
        eventId = ""
        cartId = ""
        payload = ""
        time = 0
    }

}