package com.gamasoft.functionalcqrs.application

import com.gamasoft.functionalcqrs.eventStore.EventStoreInMemory
import com.gamasoft.functionalcqrs.readModel.ReadEntity
import com.gamasoft.functionalcqrs.readModel.Query
import com.gamasoft.functionalcqrs.readModel.QueryHandler
import com.gamasoft.functionalcqrs.writeModel.CmdResult
import com.gamasoft.functionalcqrs.writeModel.Command
import com.gamasoft.functionalcqrs.writeModel.CommandHandler
import com.gamasoft.functionalcqrs.writeModel.Result
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.runBlocking


class Application {

    val eventStore = EventStoreInMemory()

    val commandHandler = CommandHandler(eventStore)
    val queryHandler = QueryHandler()

    fun start() {
        eventStore.addListener(queryHandler.eventChannel)
        eventStore.loadAllEvents()
    }

    fun stop() {
        eventStore.saveAllEvents()

    }

    fun Command.process(): CompletableDeferred<CmdResult> {
        return commandHandler.handle(this)
    }

    fun Query<out ReadEntity>.process(): List<ReadEntity> {
        return queryHandler.handle(this)
    }


    fun List<Command>.processAllInSync(): List<String> {

        val completed = runBlocking {
             this@processAllInSync.map { it.process() }
                    .map { it.await() }
        }


        return completed.filter { it is Result.Error }
            .map { (it as Result.Error).errorMsg }
    }

}


