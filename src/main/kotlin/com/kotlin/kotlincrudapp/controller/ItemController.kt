package com.kotlin.kotlincrudapp.controller

import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(val service: ItemService) {
    @GetMapping("/")
    fun index(): List<Item> = service.findItems()

    @PostMapping("/item")
    fun post(@RequestBody item: Item) {
        service.save(item)
    }
}