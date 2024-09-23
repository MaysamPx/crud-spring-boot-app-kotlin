package com.kotlin.kotlincrudapp.service

import com.kotlin.kotlincrudapp.model.Item
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemService(val db: JdbcTemplate) {

    fun findItems(): List<Item> = db.query("SELECT * FROM items ORDER BY name") { resultSet, _ ->
        Item(resultSet.getString("id"), resultSet.getString("name"), resultSet.getDouble("price"))
    }

    fun save(item: Item) {
        val id = item.id ?: UUID.randomUUID().toString()
        db.update(
            "INSERT INTO items VALUES (? ,? ,?)",
            id, item.name, item.price
        )
    }
}