package io.sample.cart.store.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<CartJpo, String> {}