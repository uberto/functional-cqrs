package com.gamasoft.functionalcqrs

import com.gamasoft.functionalcqrs.application.Application

import com.gamasoft.functionalcqrs.readModel.Query
import com.gamasoft.functionalcqrs.writeModel.Command

fun main(args: Array<String>) {

    println("Hello world!")

    Application().apply {
        start()

        val errors = listOf<Command>(
         //   StartOrder("123"),
         //   AddItem("123", "pizza margherita", 2),
         //   Confirm("123")
        //etc.
                 ).processAllInSync()


        println("Errors: $errors") //TODO fix me

        listOf<Query<*>>(
//            GetAllOpenOrders,
//            GetOrder("123"),
 //etc.
        ).forEach {
                println("Processed Query $it with result ${it.process()}")
        }
    }


}