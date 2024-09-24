package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.model.ItemType
import com.kotlin.kotlincrudapp.repository.ItemRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.util.*
import java.util.UUID.randomUUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ItemServiceTest {
    private val itemRepository: ItemRepository = mock(ItemRepository::class.java)
    private val itemService = ItemService(itemRepository)

    @Test
    @DisplayName("When call findItems then return all items")
    fun testFindItemsReturnsAllItems() {
        val mockItems = listOf(
            Item(randomUUID().toString(), "Bread", 2.5, ItemType.BAKERY),
            Item(randomUUID().toString(), "Cola", 3.5, ItemType.DRINKS),
            Item(randomUUID().toString(), "Croissant", 0.99, ItemType.BAKERY)
        )

        `when`(itemRepository.findAll()).thenReturn(mockItems)

        val items = itemService.findItems()

        assertEquals(3, items.size)
        assertEquals("Bread", items[0].name)
        assertEquals(ItemType.BAKERY, items[0].type)

        assertEquals("Cola", items[1].name)
        assertEquals(ItemType.DRINKS, items[1].type)

        assertEquals("Croissant", items[2].name)
        assertEquals(ItemType.BAKERY, items[2].type)

    }

    @Test
    @DisplayName("When call findItemById then return item by id")
    fun `should return item by id` () {
        val id: String = randomUUID().toString()
        val mockItem = Item(id, "Bread", 2.5, ItemType.BAKERY)

        `when`(itemRepository.findById(id)).thenReturn(Optional.of(mockItem))

        val item = itemService.findItemById(id)

        assertNotNull(item)
        assertEquals(id, item[0].id)
        assertEquals("Bread", item[0].name)
    }

    @Test
    @DisplayName("When call save then persist item")
    fun testSaveItem() {
        val id: String = randomUUID().toString()
        val newItem = Item(id, "Bread", 2.5, ItemType.BAKERY)

        itemService.save(newItem)

        verify(itemRepository).save(newItem)
    }

}