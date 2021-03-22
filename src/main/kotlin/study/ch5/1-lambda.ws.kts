
data class Person(val name: String, val age: Int)

val people = listOf(Person("Kim", 14), Person("Lee", 10))
// 아래 2개는 같이 사용
people.maxByOrNull { it.age }
people.maxByOrNull(Person::age)

// 람다 예시

val sum = { x: Int, y: Int -> x + y }
sum(1,2)

run { println("람다 바로 호출")} // run 키워드를 활용하여 바로 호출

people.maxByOrNull { p -> p.age }

people.joinToString(separator = "||", transform = { it.name })
people.joinToString(separator = "||", transform = { p: Person -> p.name })
people.joinToString(separator = "^")  { p: Person -> p.name } // 마지막 인자라 뺄 수 있음


// 외부 변수 캡쳐
fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        println("$prefix $it") // 람다 안에서 외부 파라미터 prefix 접근
    }
}
var errors = listOf("403 Forbidden", "404 Not found")
printMessageWithPrefix(errors, "ERROR")

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if(it.startsWith("4")) clientErrors ++  // 로컬 변수
        else if(it.startsWith("5")) serverErrors++
    }
    println("Server error count : $serverErrors, Client Error count $clientErrors")
}
printProblemCounts(errors)

// 멤버 참조

errors.forEach(::println) // :: 이게 멤버 참조

data class Mock(val mockName: String)

val createMock = ::Mock // 생성자도 멤버참조로 전달 가능

val m = createMock("Mock")
m

m::mockName // m 객체의 mockName 을 가져옴 (파라미터가 없는 함수)
Mock::mockName // 얘는 호출시 Mock 객체를 파라미터로 넘겨야함

