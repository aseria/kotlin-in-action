package coroutine.part1

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine
import kotlin.coroutines.resume
import kotlinx.coroutines.runBlocking

//val ret: Unit =
//    suspendCoroutine<Unit> { cont: Continuation<Unit> ->
//        cont.resume(Unit)
//    }

suspend fun resumeTest() {
    val i:Int = suspendCoroutine{ cont ->
        cont.resume(42)
    }
    println(i)

    val str:String = suspendCoroutine{ cont ->
        cont.resume("Text")
    }
    println(str)

    val b:Boolean = suspendCoroutine{ cont ->
        cont.resume(false)
    }
    println(b)
}
runBlocking { resumeTest() }


data class User(val name: String)
fun requestUser(callback: (User) -> Unit) {
    Thread.sleep(1000)
    val user = User("String")
    callback(user)
}
suspend fun resumeWithObject() {
    println("Before")
    val user = suspendCoroutine<User> { cont ->
        requestUser { user ->
            cont.resume(user)
        }
    }
    println(user)
    println("After")
}
runBlocking { resumeWithObject()}

//suspendCoroutine 을 함수내부로 옮기면
suspend fun requestUser(): User {
    return suspendCoroutine<User> { cont ->
        requestUser { user ->
            cont.resume(user)
        }
    }
}
suspend fun resumeWithObject2() {
    println("Before")
    val user = requestUser()
    println(user)
    println("After")
}
runBlocking { resumeWithObject2()}


/** recommended **/
suspend fun requestUserCancellable(): User {
    return suspendCancellableCoroutine { cont ->
        requestUser { user ->
            cont.resume(user)
        }
    }
}
suspend fun resumeWithObject3() {
    println("Before")
    val user = requestUserCancellable()
    println(user)
    println("After")
}
runBlocking { resumeWithObject3()}

