package com.kotlin.kotlincrudapp.controller

import com.kotlin.kotlincrudapp.model.Order
import com.kotlin.kotlincrudapp.service.OrderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class OrderController(var orderService: OrderService) {
    @GetMapping("/orders")
    fun getAllOrders(): List<Order> = orderService.findOrders()

    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable("id") id : UUID): List<Order> = orderService.findOrderByID(id)

    @PostMapping("/orders")
    fun submitOrder(@RequestBody order: Order) {
        orderService.save(order)
    }
}