package coroutine.part1

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


suspend fun suspended() {
    println("Before")
    suspendCoroutine<Unit> { }
    println("After")
}
//Before

suspend fun suspendedWithLambda() {
    println("Before")
    suspendCoroutine<Unit> { continuation ->
        println("Before too")
    }
    println("After")
}
//Before
//Before too
suspend fun suspendedWithResume() {
    println("Before")
    suspendCoroutine<Unit> { continuation ->
        continuation.resume(Unit)
    }
    println("After")
}
//Before
//After

suspend fun suspendedWithThreadSleep() {
    println("Before")
    suspendCoroutine<Unit> { continuation ->
        thread {
            println("Suspended")
            Thread.sleep(1000)
            continuation.resume(Unit)
            println("Resumed")
        }
    }
    println("After")
}
// Before
// Suspended
// (1 second delay)
// After
// Resumed

fun invokeAfterSecond(operation: () -> Unit) {
    thread {
        Thread.sleep(1000)
        operation.invoke()
    }
}
suspend fun suspendedWithThreadSleepInOtherFunction() {
    println("Before")
    suspendCoroutine<Unit> { continuation ->
        invokeAfterSecond { continuation.resume(Unit) }
    }
    println("After")
}
// Before
// (1 second delay)
// After


private val executor = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler").apply { isDaemon = true }
}

suspend fun suspendedWithThreadSleepInSingleThread() {
    println("Before")
    suspendCoroutine<Unit> { continuation ->
        executor.schedule({continuation.resume(Unit)}, 1, TimeUnit.SECONDS)
    }
    println("After")
}


/*** Finally Delay function! ***/
suspend fun delay(time: Long): Unit =
    suspendCoroutine { cont ->
        executor.schedule({
            cont.resume(Unit)
        }, time, TimeUnit.MILLISECONDS)
    }
suspend fun suspendedWithDelay() {
    println("Before")
    delay(1000)
    println("After")
}

suspend fun main() {
    suspendedWithDelay()
}