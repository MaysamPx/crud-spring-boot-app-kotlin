package com.kotlin.kotlincrudapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("orders")
data class Order(@Id var id: UUID?, val itemId: UUID, val price: Double, val amount: Double)
