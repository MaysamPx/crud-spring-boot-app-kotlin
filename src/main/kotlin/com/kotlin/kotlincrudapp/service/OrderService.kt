package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.model.Order
import com.kotlin.kotlincrudapp.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.toList

@Service
class OrderService(val orderRepository: OrderRepository) {
    fun findOrders(): List<Order> = orderRepository.findAll().toList()
    fun findOrderByID(id:UUID): List<Order> = orderRepository.findById(id).toList()
    fun save(order: Order): Order = orderRepository.save(order)
}