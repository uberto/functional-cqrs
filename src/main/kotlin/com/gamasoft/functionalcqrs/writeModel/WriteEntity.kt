package com.gamasoft.functionalcqrs.writeModel

import com.gamasoft.functionalcqrs.eventStore.*


interface EventComposable<T: Event> {
    fun compose(e: T): EventComposable<T>
}


//sealed class Order(val id: String): EventComposable<OrderEvent>{
//    override abstract fun compose(e: OrderEvent): Order
//}
//
//sealed class Item(val id: String): EventComposable<ItemEvent>{
//    override abstract fun compose(e: ItemEvent): Item
//}

//object emptyOrder: Order("") {
//    override fun compose(e: OrderEvent) = when (e) {
//        is Started -> NewOrder(e.phoneNum, emptyList())
//        else -> this //ignore other events
//    }
//}
//
//data class NewOrder(val phoneNum: String, val details: List<OrderDetail>): Order(phoneNum) {
//    override fun compose(e: OrderEvent) = when (e) {
//        is ItemAdded -> NewOrder(phoneNum, details.plus(OrderDetail(e.itemId, e.quantity)))
//        is AddressAdded -> ReadyOrder(phoneNum, e.address, details)
//        is Cancelled -> CancelledOrder(phoneNum)
//        else -> this
//    }
//}
///etc

