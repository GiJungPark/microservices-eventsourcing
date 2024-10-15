package io.sample.cart.event

import java.util.*

class ItemRemoved(
    private val eventId: String = UUID.randomUUID().toString(),
    private val time: Long = System.currentTimeMillis(),

    private val productNo: String
)