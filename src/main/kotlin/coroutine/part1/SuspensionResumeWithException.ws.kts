import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MyException(override val message: String = ""): Throwable("Just an exception : $message")

suspend fun resumeWithException() {
    try {
        suspendCancellableCoroutine<Unit> { cont ->
            cont.resumeWithException(MyException())
        }
    } catch (e: MyException) {
        println("Caught: " + e.message)
    }
}
runBlocking { resumeWithException() }

data class User(val name: String)
data class UserResponse(val isSuccessful: Boolean, val data: User)
data class News(val name: String)
fun requestUser(callback: (UserResponse) -> Unit) {
    Thread.sleep(1000)
    val user = User("String")
    val response = UserResponse(false, user)
    callback(response)
}

suspend fun requestUser(): User {
    return suspendCancellableCoroutine<User> { cont ->
        requestUser { resp ->
            if(resp.isSuccessful) {
                cont.resume(resp.data)
            } else {
                val e = MyException()
                cont.resumeWithException(e)
            }
        }
    }
}

fun requestNews(onSuccess: (News) -> Unit, onError: (Throwable) -> (Unit)) {
    try {
        val user = User("John")
        if(user.name == "John") {
            onSuccess(News("Success News"))
        } else {
            onError(MyException("User Name Error"))
        }
    } catch (e: Throwable) {
        onError(e)
    }
}
suspend fun requestNews() {
    println("Before")
    try {
        val news = suspendCancellableCoroutine<News> { cont ->
            requestNews(
                onSuccess = { news -> cont.resume(news) },
                onError = { e -> cont.resumeWithException(e) }
            )
        }
        println(news)
    } catch (e: Throwable) {
        println(e.message)
    }
    println("After")
}
runBlocking { requestNews() }