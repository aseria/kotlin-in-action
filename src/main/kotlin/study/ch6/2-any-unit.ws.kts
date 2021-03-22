// Any, Any? 
//java.lang.Object 에 대응 
// 그러나Object 에 있는 wait/notify 등은 사용 불가

//Unit
// java void 와 댕으
// Unit 은 타입으로 사용 가능한것이 다른점

interface Process<T> {
    fun process(): T
}

class NoResultProcess: Process<Unit> {
    override fun process() {
        println("124") // 리턴 안해도됨 (Unit 이니까)
    }
}


//Nothing
// 이 함수는 절대 정상적으로 끝나지 않음.. exception만 발생하는 경우와 같이
fun fail(message: String) : Nothing {
    throw IllegalStateException(message)
}

fun nullableValue(): String? {
    return ""
}
val address = nullableValue() ?: fail("Fail")

