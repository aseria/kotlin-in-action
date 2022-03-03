import kotlinx.coroutines.*

suspend fun i(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000) { i ->
            Thread.sleep(200) // We might have some
            // complex operations or reading files here
            println("Printing $i")
        }
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
    delay(1000)
}


suspend fun j(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        repeat(1_000) { i ->
            Thread.sleep(200)
            yield() //yield 를 통해 중간 중단점 지정
            println("Printing $i")
        }
    }
    delay(1025)
    job.cancelAndJoin()

    println("Cancelled successfully3")
    delay(1000)
}
// Printing 0
// Printing 1
// Printing 2
// Printing 3
// Printing 4
// Printing 5
// Cancelled successfully


//active 상태인지 추적
public val CoroutineScope.isActive: Boolean
    get() = coroutineContext[Job]?.isActive ?: true

suspend fun k(): Unit = coroutineScope {
    val job = Job()
    launch(job) {
        var idx = 0
        do {
            Thread.sleep(200)
            println("Printing $idx")
            idx += 1
        } while (isActive)
    }
    delay(1100)
    job.cancelAndJoin()
    println("Cancelled successfully")
}
// Printing
// Printing
// Printing
// Printing
// Printing
// Printing
// Cancelled successfully
runBlocking {
   k()
}