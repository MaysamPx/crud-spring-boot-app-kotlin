package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.model.Order
import com.kotlin.kotlincrudapp.repository.OrderRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*
import java.util.UUID.randomUUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class OrderServiceTest {

    @Mock
    private lateinit var orderRepository: OrderRepository

    private lateinit var orderService: OrderService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        orderService = OrderService(orderRepository) // Create service with mocked repo
    }

    @Test
    @DisplayName("When call findOrders then return all orders")
    fun testFindOrdersReturnsAllOrders() {
        val mockOrders: List<Order> = listOf(
            Order(randomUUID(), randomUUID(), BigDecimal(3.5), BigDecimal(12)),
            Order(randomUUID(), randomUUID(), BigDecimal(1.99), BigDecimal(9)),
            Order(randomUUID(), randomUUID(), BigDecimal(4.99), BigDecimal(6))
        )

        `when`(orderRepository.findAll()).thenReturn(mockOrders)

        val orders = orderService.findOrders()

        assertEquals(orders, mockOrders)
        assertEquals(3, mockOrders.size)

    }

    @Test
    @DisplayName("When call findOrderById then return one order")
    fun testFindOrdersByIdReturnsOrderById() {
        val id = randomUUID()
        val itemId = randomUUID()

        val mockOrder = Order(id, itemId, BigDecimal(5.0), BigDecimal(7))

        `when`(orderRepository.findById(id)).thenReturn(Optional.of(mockOrder))

        val order = orderRepository.findById(id)

        assertEquals(id, mockOrder.id)
        assertNotNull(order)
        assertEquals(itemId, order.get().itemId)
    }

    @Test
    @DisplayName("When call save then persist order")
    fun testSaveOrder() {
        val newOrder = Order(randomUUID(), randomUUID(), BigDecimal("5.0"), BigDecimal("7"))
        `when`(orderRepository.save(newOrder)).thenReturn(newOrder)

        orderService.save(newOrder)

        verify(orderRepository).save(newOrder)
    }

}
