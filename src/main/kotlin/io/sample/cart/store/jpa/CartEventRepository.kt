package io.sample.cart.store.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CartEventRepository : JpaRepository<CartEventJpo, String> {}