package com.gamasoft.functionalcqrs.application

import assertk.assert
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.gamasoft.functionalcqrs.readModel.*
import com.gamasoft.functionalcqrs.writeModel.*
import kotlinx.coroutines.experimental.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ApplicationTest {

    val application = Application()

    @BeforeEach
    fun setUp(){
        application.start()
    }
//
//    @Test
//    fun newOrder() {
//        val pn = "101"
//        application.apply{
//            val r = StartOrder(pn).process()
//
//            Thread.sleep(10) //async so we have to wait
//
//            val os = GetOrder(pn).process()
//
//            assert(os).hasSize(1)
//            assert((os[0] as ReadOrder) ).isEqualTo(ReadOrder(OrderStatus.new, pn, 0.0, null, mutableListOf()))
//
//
//            val eos = GetOrder("***").process()
//            assert(eos).isEmpty()
//        }
//    }
//
//    @Test
//    fun newItem() {
//
//        val id = "CAPRI"
//
//        application.apply {
//            val r = CreateItem(id, "pizza capricciosa", 7.5).process()
//
//            runBlocking {
//                val errors =r.await()
//                assert(errors.isValid)
//            }
//
//            val os = GetItem(id).process()
//            assert(os).hasSize(1)
//            assert((os[0] as ReadItem)).isEqualTo(ReadItem("pizza capricciosa", 7.5, true))
//
//            val eos = GetItem("***").process()
//            assert(eos).isEmpty()
//        }


//etc.


}

