package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.exception.ItemNotFoundException
import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.repository.ItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemService(val itemRepository: ItemRepository) {

    fun findItems(): List<Item> = itemRepository.findAll().toList()

    fun findItemById(id: UUID): Item = itemRepository.findById(id).orElseThrow { ItemNotFoundException("Item not found") }

    fun save(item: Item) {
        itemRepository.save(item)
    }

    
}