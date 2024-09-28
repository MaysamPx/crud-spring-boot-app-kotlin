package com.kotlin.kotlincrudapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("orders")
data class Order(@Id var id: UUID?, val itemId: UUID, val price: BigDecimal, val amount: BigDecimal)
