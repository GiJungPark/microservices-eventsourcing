package io.sample.cart.event

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

@Serializable
abstract class Event {
    private val eventId: String
    private val time: Long
    private lateinit var cartId: String

    constructor() {
        this.eventId = UUID.randomUUID().toString()
        this.time = System.currentTimeMillis()
    }

    fun getCartId(): String {
        return cartId
    }

    fun setCartId(cartId: String) {
        this.cartId = cartId
    }

    fun getEventId(): String {
        return eventId
    }

    fun getTime(): Long {
        return time
    }

    fun getPayLoad(): String {
        return Json.encodeToString(this)
    }
}