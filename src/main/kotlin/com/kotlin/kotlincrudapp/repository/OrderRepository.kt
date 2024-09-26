package com.kotlin.kotlincrudapp.repository

import com.kotlin.kotlincrudapp.model.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, String>