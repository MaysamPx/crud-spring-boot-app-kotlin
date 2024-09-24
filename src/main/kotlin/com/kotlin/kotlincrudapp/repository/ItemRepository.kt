package com.kotlin.kotlincrudapp.repository

import com.kotlin.kotlincrudapp.model.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, String>