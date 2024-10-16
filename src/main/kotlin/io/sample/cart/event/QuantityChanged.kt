package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class QuantityChanged(
    val cartId: String,

    val productNo: String,
    val quantity: Int,

    override val eventId: String = UUID.randomUUID().toString(),
    override val time: Long = System.currentTimeMillis(),
) : Event()