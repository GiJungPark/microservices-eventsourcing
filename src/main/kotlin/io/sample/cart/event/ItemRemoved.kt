package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ItemRemoved(
    val cartId: String,

    val productNo: String,

    override val eventId: String = UUID.randomUUID().toString(),
    override val time: Long = System.currentTimeMillis(),
) : Event()
