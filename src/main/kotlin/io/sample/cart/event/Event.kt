package io.sample.cart.event

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

@Serializable
sealed class Event {
    abstract val eventId: String
    abstract val time: Long

    fun getPayLoad(): String {
        return Json { ignoreUnknownKeys = true }.encodeToString(this)
    }

    fun typeName(): String {
        return this.javaClass.typeName
    }
}
