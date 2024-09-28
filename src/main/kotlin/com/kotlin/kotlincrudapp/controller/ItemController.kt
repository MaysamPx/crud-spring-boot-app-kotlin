package com.kotlin.kotlincrudapp.controller

import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class ItemController(val service: ItemService) {
    @GetMapping("/")
    fun index(): List<Item> = service.findItems()

    @GetMapping("/{id}")
    fun index(@PathVariable("id") id: UUID): ResponseEntity<Item> {
        val item = service.findItemById(id)
        return item.let { ResponseEntity.ok(it) }
    }

    @PostMapping("/item")
    fun post(@RequestBody item: Item) {
        service.save(item)
    }
}