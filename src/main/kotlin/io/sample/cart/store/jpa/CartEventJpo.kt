package io.sample.cart.store.jpa

import io.sample.cart.aggregate.Cart
import io.sample.cart.event.Event
import io.sample.cart.event.EventSerializer
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import kotlinx.serialization.json.Json

@Entity
@Table(name = "TB_CART_EVENT")
class CartEventJpo {
    @Id
    private val eventId: String
    private val cartId: String
    private val type: String

    @Lob
    private val payload: String
    private val time: Long

    constructor(cart: Cart, event: Event) {
        eventId = event.eventId
        cartId = cart.getCartId()

        payload = event.getPayLoad()
        type = event.typeName()
        time = event.time
    }

    constructor() {
        eventId = ""
        cartId = ""

        payload = ""
        type = ""
        time = 0
    }

    fun toEvent(): Event {
        val event = Json { ignoreUnknownKeys = true }.decodeFromString(EventSerializer, payload)
        return event
    }
}