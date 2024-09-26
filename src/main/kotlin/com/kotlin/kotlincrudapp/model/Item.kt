package com.kotlin.kotlincrudapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("items")
data class Item(@Id var id: String?, val name: String, val price: Double, val type : ItemType)
