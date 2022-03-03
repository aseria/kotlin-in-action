import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun a() = coroutineScope {
    val job = launch {
        repeat(1_000) { i ->
            delay(100)
            Thread.sleep(100) // We simulate long operation
            println("Printing $i")
        }
    }

    delay(1000)
    job.cancel()
    println("Cancelled successfully")
}


suspend fun b(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000) { i ->
            delay(200)
            println("Printing $i")
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
}


//exception
suspend fun c(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            repeat(1_000) { i ->
                delay(200)
                println("Printing $i")
            }
        } catch (e: CancellationException) {
            println(e)
            throw e //다시 thorw 할 것 그래야 전파가되니까?
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
    delay(1000)
}

suspend fun d(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(Random.nextLong(2000))
            println("Done")
        } finally {
            // 여기서 리소스를 해제할 것
            print("Will always be printed") 
        }
    }
    delay(1000)
    job.cancelAndJoin()
}
//one more call
suspend fun e(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(200)
            println("Job is done")
        } finally {
            println("Finally")
            launch { // will be ignored
                println("Will not be printed")


            }
            delay(100) // here exception is thrown
            println("Will not be printed")
        }
    }
    delay(100)
    job.cancelAndJoin()
    println("Cancel done")
}
// Finally
// Cancel done


//withContext(NonCancellable)
suspend fun f(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        try {
            delay(200)
            println("Coroutine finished")
        } finally {
            println("Finally")
            withContext(NonCancellable) {
                delay(1000L)
                println("Cleanup done")
            }
        }
    }
    delay(100)
    job.cancelAndJoin()
    println("Done")
}
// Finally
// Cleanup done
// Done

// Invock on Completion
suspend fun g(): Unit = coroutineScope {
    val job = launch {
        delay(1000)
    }
    job.invokeOnCompletion { exception: Throwable? ->
        println(exception?.message)
        println(exception?.let { it::class.java }) //class kotlinx.coroutines.JobCancellationException
        println("Finished")
    }
    delay(400)
    job.cancelAndJoin()
}
// Finished

suspend fun h(): Unit = coroutineScope {
    val job = launch {
        delay(Random.nextLong(1000, 2400))
        println("Finished")
    }
//    delay(800)
    job.invokeOnCompletion { exception: Throwable? ->
        println("Will always be printed")
        println("The exception was: $exception")
    }
    delay(800)
    job.cancelAndJoin()
}
// Will always be printed
// The exception was:
// kotlinx.coroutines.JobCancellationException
// (or)
// Finished
// Will always be printed
// The exception was null


