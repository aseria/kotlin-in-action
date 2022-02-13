import coroutine.part1.delay
import coroutine.part1.suspended
import kotlin.coroutines.Continuation

/*** 1. Continuation-passing style ***/

data class User(val name: String)
suspend fun getUser(): User? = null
// --> 아래처럼 변환
fun getUser(cont: Continuation<*>): Any? = null

//Any 로 바뀌는 이유
// User 객체를 리턴하거나 COROUTINE_SUSPENDE 를 리턴하기 때문!

// -- Simple Case --
suspend fun MyFunction() {
    println("Before")
    delay(1000)
    println("After")
}
// 아래 처럼 변환될 듯
//설명을 위한 객체
data class MyFunctionContinuation(val continuation: Continuation<*>)
fun myFunctionUnderHood(continuation: Continuation<*>): Any {
    val continuation = MyFunctionContinuation(continuation)
}
