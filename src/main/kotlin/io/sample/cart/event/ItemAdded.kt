package io.sample.cart.event

import java.util.*

class ItemAdded (
    private val eventId: String = UUID.randomUUID().toString(),
    private val time: Long = System.currentTimeMillis(),

    private val productNo: String,
    private val productName: String,
    private val quantity: Int
)