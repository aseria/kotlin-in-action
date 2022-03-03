package coroutine.part

import kotlinx.coroutines.*

//suspend fun main() = coroutineScope {
//    val job = Job()
//    println(job) // JobImpl{Active}@ADD
//    job.complete()
//    println(job) // JobImpl{Completed}@ADD
//
//    val activeJob = launch {
//        // no-op
//    }
//    println(activeJob) // StandaloneCoroutine{Active}@ADD
//    activeJob.join()
//    println(activeJob) // StandaloneCoroutine{Completed}@ADD
//
//    val lazyJob = launch(start = CoroutineStart.LAZY) {
//        // no-op
//    }
//    println(lazyJob) // LazyStandaloneCoroutine{New}@ADD
//    lazyJob.start()
//    println(lazyJob) // LazyStandaloneCoroutine{Active}@ADD
//    lazyJob.join()
//    println(lazyJob) //LazyStandaloneCoroutine{Completed}@ADD
//}

//fun main(): Unit = runBlocking {
//    val job: Job = launch {
//        delay(1000)
//        println("Test")
//    }
//
//    println(job)
//}

//fun main(): Unit = runBlocking {
//    val job: Job = launch {
//        delay(1000)
//    }
//
//    val parentJob: Job = coroutineContext.job
//    // or coroutineContext[Job]!!
//    println(job == parentJob) // false
//    val parentChildren: Sequence<Job> = parentJob.children
//    println(parentChildren.first() == job) // true
//}


//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) { // the new job replaces one from parent
//        delay(1000)
//        println("Text 1")
//    }
//    launch(job) { // the new job replaces one from parent
//        delay(2000)
//        println("Text 2")
//    }
//    job.completeExceptionally()
//    job.join() // Here we will await forever
//
//    println("Will not be printed")
//}
//// (1 sec)
//// Text 1
//// (1 sec)
//// Text 2
//// (runs forever)
//suspend fun main() = coroutineScope {
//    val job = launch {
//        repeat(1_000) { i ->
//            delay(100)
//            Thread.sleep(100) // We simulate long operation
//            println("Printing $i")
//        }
//    }
//    delay(1000)
//    job.cancel()
//    job.join()
//    println("Cancelled successfully")
//}
// Printing 0
// Printing 1
// Printing 2
// Printing 3
// Cancelled successfully
// Printing 4
//sampleEnd

//suspend fun main() = coroutineScope {
//    launch {  }
//    async {}
//    println(coroutineContext)
//}
//
//suspend fun a() = coroutineScope {
//    async {
//
//    }
//}


public val CoroutineScope.isActive: Boolean
    get() = coroutineContext[Job]?.isActive ?: true

suspend fun k(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        var idx = 0
        do {
            withContext(Dispatchers.IO) {
                Thread.sleep(200)
            }
//            ensureActive()
            println("Printing $idx : $isActive")
            idx += 1
        } while (isActive)
    }
    delay(1000)
    println("===")
    job.cancelAndJoin()
    println("Cancelled successfully")
}

suspend fun l(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1000) { num ->
            Thread.sleep(200)
            ensureActive()
            println("Printing $num")
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
}


fun m(): Unit = runBlocking {
// Don't do that, SupervisorJob with one children
// and no parent works similar to just Job
    launch(SupervisorJob()) {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }

    }

    println("Will be printed?")
    delay(3000)
    println("Will be printed??")
}
// Exception...

fun n(): Unit = runBlocking {
    supervisorScope {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            println("Will be printed")
        }
    }
    delay(1000)
    println("Done")
}

fun main(): Unit {
    runBlocking {
       dd()
    }
}

suspend fun dd() = coroutineScope {
    val str1 = async<String> {
        delay(1000)
        throw MyException()
    }

    val str2 = async {
        delay(2000)
        "Text2"
    }

    try {
        println(str1.await())
    } catch (e: MyException) {
        println(e)
    }

    println(str2.await())
}

class MyException : Throwable() {

}
// MyException
// Text2