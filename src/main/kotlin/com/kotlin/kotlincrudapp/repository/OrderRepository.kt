package com.kotlin.kotlincrudapp.repository

import com.kotlin.kotlincrudapp.model.Order
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OrderRepository : CrudRepository<Order, UUID>