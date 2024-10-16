package io.sample.cart.event

import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

// TODO: 필드에 의존하는 방법 말고 더 좋은 방법이 없을까?
object EventSerializer : JsonContentPolymorphicSerializer<Event>(Event::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "productName" in element.jsonObject && "price" in element.jsonObject -> ItemAdded.serializer()
        "quantity" in element.jsonObject -> QuantityChanged.serializer()
        else -> ItemRemoved.serializer()
    }
}