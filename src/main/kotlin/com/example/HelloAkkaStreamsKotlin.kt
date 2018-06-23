package com.example

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.javadsl.Source

fun main(args : Array<String>) {

    val sys = ActorSystem.apply("my-test-system")
    val mat = ActorMaterializer.create(sys)

    Source.repeat(NotUsed.getInstance()).zipWithIndex()
            .map { it.second() * 2 }
            .take(100)
            .runForeach({ println(it) }, mat)
            .thenRun { sys.terminate() }

}

