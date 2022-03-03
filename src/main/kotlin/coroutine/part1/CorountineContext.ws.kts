import kotlinx.coroutines.*
import kotlin.coroutines.*

suspend fun printName() {
//    val context = continuation.context
    println(currentCoroutineContext()[CoroutineName]?.name)
}

suspend fun runPrint() = withContext(CoroutineName("Outer")) {
    printName() // Outer
    launch(CoroutineName("Inner")) {
        printName() // Inner
    }
    delay(10)
    printName() // Outer
}

runBlocking { runPrint() }

class CounterContext(
    private val name: String
) : CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key
    private var nextNumber = 0

    fun printNext() {
        println("$name: $nextNumber")
        nextNumber++
    }

    companion object Key:CoroutineContext.Key<CounterContext>
}

suspend fun printNext() {
    currentCoroutineContext()[CounterContext]?.printNext()
}

suspend fun run(): Unit =
    withContext(CounterContext("Outer")) {
        printNext() // Outer: 0
        launch {
            printNext() // Outer: 1
            launch {
                printNext() // Outer: 2
            }
            launch(CounterContext("Inner")) {
                printNext() // Inner: 0
                printNext() // Inner: 1
                launch {
                    printNext() // Inner: 2
                }
            }
        }
        printNext() // Outer: 3
    }
runBlocking { run() }