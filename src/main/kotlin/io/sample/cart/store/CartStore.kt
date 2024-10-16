package io.sample.cart.store

import io.sample.cart.aggregate.Cart
import io.sample.cart.store.jpa.CartEventJpo
import io.sample.cart.store.jpa.CartEventRepository
import io.sample.cart.store.jpa.CartJpo
import io.sample.cart.store.jpa.CartRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrElse


@Repository
@Transactional
class CartStore(
    private val cartRepository: CartRepository,
    private val cartEventRepository: CartEventRepository,
) {

    fun save(cart: Cart) {
        cartRepository.save(CartJpo(cart))
        val eventJpos = cart.getEvents().stream()
            .map { event -> CartEventJpo(cart, event) }
            .collect(Collectors.toList())
        cartEventRepository.saveAll(eventJpos)
    }

    fun load(cartId: String): Cart? {
        val cartJpo =
            cartRepository.findById(cartId).getOrElse { throw IllegalArgumentException("CartEvent not found: $cartId") }
        val eventJpos = cartEventRepository.findByCartIdOrderByTimeAsc(cartId)

        val foundCart = cartJpo.toCart()

        val events = eventJpos.stream()
            .map{eventJpo -> eventJpo.toEvent()}
            .collect(Collectors.toList())

        events.forEach {event -> foundCart.apply(event, false)}

        return foundCart
    }
}