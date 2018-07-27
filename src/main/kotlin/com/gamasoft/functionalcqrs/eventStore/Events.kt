package com.gamasoft.functionalcqrs.eventStore

import java.time.Instant


sealed class Event(val pk: String) {
    val created = Instant.now()
    val version = 0
}

//sealed class OrderEvent(val key: String): Event(key)
//data class Started(val phoneNum: String): OrderEvent(phoneNum)
//
//sealed class ItemEvent(val key: String): Event(key)
//data class ItemCreated(val itemId: String, val desc: String, val price: Double): ItemEvent(itemId)
//
//etc.

