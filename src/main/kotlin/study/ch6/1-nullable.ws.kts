//? 를 붙이면 nullable 한 타입이됨

fun printStr(str: String) = println(str)

//printStr(null) : 컴파일 에러
fun printStrNullable(str: String?) = println(str)
printStrNullable(null)

// ?. 는 if(this != null) func else null 과 같음
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee) : String ? = employee.manager?.name // manager 가 null 이 아닐때만 name에 접근

//Elvis Operator (?:
fun foo(s: String?) {
    val t : String = s ?: "" // ?: 기본값 처리
}

// throw 문도 ?: 와 사용 가능
fun validate(s: String?) : Boolean{
    return s?.startsWith("Lee") ?: throw IllegalArgumentException("Error") // 확인후 안되면 에러 처리
}

fun checkAndTest(s: String?) {
    val sub = s?.substring(1..3) ?: throw IllegalArgumentException("Error") // sub 는 이제 String 타입임 (Not null)
    with(sub) {
        println(this)
    }
}

// Safe Type Cast
class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person ?: return false // 안전하게 타입 변환 아니면 return
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }
}

// !! 알고 있지만 쓰지는 말것 명확할때만 쓰기

// let
fun sendEmail(email: String) {
    println("Send email to $email")
}
val email: String? = "asdadw@asdasd.net"

email?.let {
    sendEmail(it)
}

// 나중에 초기화 (lateinit)
class Service {
    fun action() : String = "Action"
}
class Test {
    private lateinit var service: Service // lateinit 이라 var 라.. 좋은지는 모르겠음 
    //Spring 에서도 생성자 주입으로 그랫 구현하도록 노력함
}

