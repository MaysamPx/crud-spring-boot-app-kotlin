package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.repository.ItemRepository
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.jvm.optionals.toList

@Service
class ItemService(val itemRepository: ItemRepository) {

    fun findItems(): List<Item> = itemRepository.findAll().toList()
    
    fun findItemById(id: UUID): List<Item> = itemRepository.findById(id).toList()

    fun save(item: Item) {
        itemRepository.save(item)
    }

    
}