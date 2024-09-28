package com.kotlin.kotlincrudapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("items")
data class Item(@Id var id: UUID?, val name: String, val price: Double, val type : ItemType)
