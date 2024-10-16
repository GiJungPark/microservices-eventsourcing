package io.sample.cart.event

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class ItemAdded(
    val cartId: String,

    val productNo: String,
    val productName: String,
    val price: Int,
    val quantity: Int,

    override val eventId: String = UUID.randomUUID().toString(),
    override val time: Long = System.currentTimeMillis(),
) : Event()