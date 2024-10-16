package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CartRemoved(
    val cartId: String,

    override val eventId: String = UUID.randomUUID().toString(),
    override val time: Long = System.currentTimeMillis(),
) : Event()