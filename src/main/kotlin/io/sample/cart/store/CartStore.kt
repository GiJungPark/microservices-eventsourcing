package io.sample.cart.store

import io.sample.cart.aggregate.Cart
import io.sample.cart.store.jpa.CartEventJpo
import io.sample.cart.store.jpa.CartEventRepository
import io.sample.cart.store.jpa.CartJpo
import io.sample.cart.store.jpa.CartRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
@Transactional
class CartStore(
    private val cartRepository: CartRepository,
    private val cartEventRepository: CartEventRepository,
) {

    fun save(cart: Cart) {
        cartRepository.save(CartJpo(cart))
        cartEventRepository.saveAll(cart.getEvents().stream()
            .map { event -> CartEventJpo(cart, event) }
            .collect(Collectors.toList()))
    }

    fun load(cartId: String): Cart? {
        // TODO
        return Cart("", mutableListOf(), mutableListOf())
    }
}