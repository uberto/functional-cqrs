package com.gamasoft.functionalcqrs.writeModel

import com.gamasoft.functionalcqrs.application.createActor
import com.gamasoft.functionalcqrs.eventStore.*
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking


sealed class Result<out A, out B> {
    class Error<A>(val errorMsg: A): Result<A, Nothing>()
    class Success<B>(val resultValue: B): Result<Nothing, B>()

}

typealias ErrorMsg = String
typealias CmdResult = Result<ErrorMsg, Event>


fun failure(errorMsg: ErrorMsg) = Result.Error(errorMsg)
fun success(resultValue: Event) = Result.Success(resultValue)


data class CommandMsg(val command: Command,
                      val response: CompletableDeferred<CmdResult>) // a command with a result


class CommandHandler(val eventStore: EventStore) {


    //if we need we can have multiple instances
    val sendChannel = createActor<CommandMsg> { executeCommand(it) }

    private fun executeCommand(msg: CommandMsg) {

        val res = executeMulti(msg.command)

        if (res is Result.Success)
            runBlocking { //use launch to store events in parallel slightly out of order
                eventStore.sendChannel.send(res.resultValue)
                delay(10) //simulate network delay
            }
        msg.response.complete(res)
    }

    private fun executeMulti(c: Command): CmdResult {

        println("Processing ${c}")

//        val cmdResult = when (c) {
////            is StartOrder -> execute(eventStore, c)
////            is AddItem -> execute(eventStore, c)
//            //etc
//        }
//        return cmdResult

        return failure("Undefined")
    }


    fun handle(cmd: Command): CompletableDeferred<CmdResult> {

        val msg = CommandMsg(cmd, CompletableDeferred())

        runBlocking { //use launch to execute commands in parallel slightly out of order
            sendChannel.send(msg)
        }

        return msg.response
    }


}
//private fun List<ItemEvent>.fold(): Item {
//    return this.fold(emptyItem) { i: Item, e: ItemEvent -> i.compose(e)}
//}

//private fun List<OrderEvent>.fold(): Order {
//    return this.fold(emptyOrder) { o: Order, e: OrderEvent -> o.compose(e)}
//}
//
//private fun execute(c: StartOrder, eventStore: EventStore): CmdResult {
//    val order:Order = eventStore.getEvents<OrderEvent>(c.phoneNum).fold()
//    return if (order == emptyOrder)
//        success(Started(c.phoneNum))
//    else
//        error("ReadOrder already existing! ${order}")
//}

//etc.




