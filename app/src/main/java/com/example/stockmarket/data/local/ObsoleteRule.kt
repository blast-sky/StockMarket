package com.example.stockmarket.data.local

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

interface ObsoleteRule {

    fun isObsolete(date: LocalDateTime): Boolean

    object Base: ObsoleteRule {
        override fun isObsolete(date: LocalDateTime): Boolean {
            val from = LocalDateTime.from(date)
            val minutes = from.until(LocalDateTime.now(), ChronoUnit.MINUTES)
            return minutes > 30
        }
    }

    object AlwaysTrue: ObsoleteRule {
        override fun isObsolete(date: LocalDateTime): Boolean = true
    }
}