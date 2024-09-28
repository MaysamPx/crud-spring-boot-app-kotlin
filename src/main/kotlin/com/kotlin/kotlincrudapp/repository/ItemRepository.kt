package com.kotlin.kotlincrudapp.repository

import com.kotlin.kotlincrudapp.model.Item
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ItemRepository : CrudRepository<Item, UUID>