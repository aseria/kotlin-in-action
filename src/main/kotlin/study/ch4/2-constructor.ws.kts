package study.ch4

class User(val name: String) //대표 생성자 //아래와 같음

//class User constructor(_name: String) {
//    val name: String
//
//    init {
//        name = _name
//    }
//}

class Person(val name: String, val isLive: Boolean = true) //생성자에도 기본 값 가능


//
open class Animal(val name: String)
class Tiger(name: String, val color: String) : Animal(name)

//
class AttributeSet
class Context
open class View {
    constructor(ctx: Context) {

    }
    constructor(ctx: Context, attr: AttributeSet) {
    }
}
class Button : View {
    constructor(ctx: Context) : super(ctx) {

    }
    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr) {
    }
}

class Button2 : View {
    constructor(ctx: Context) : this(ctx, AttributeSet()) // 아래 생성자에 위임
    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr) {
    }
}

//커스텀 게터

interface UserI {
    fun getFBName(accountId: Int) = accountId.toString() // 컴파일을 위해서 외부함수인것처럼 위장
    val name: String
}

class PrivateUser(override val name: String) : UserI
class SubUser(val email: String): UserI {
    override val name: String
        get() = email.substringBefore('@')
}


class FbUser(val accountId: Int) : UserI {
    override val name: String = getFBName(accountId) //한번말 실행된다는 점이 다름 (미리 연산하여 저장)
}

//커스텀 세터

class UserS(val name: String) {
    var address: String = "undefined"
        set(value: String) {
            println("""
                Address change for $name: 
                "$field" -> "$value"
            """.trimIndent())
            field = value
        }
}
val userS = UserS("Kim")
userS.address = "Incheon"

// 접근자 가시성
class LCounter {
    var counter: Int = 0
        private set // 외부에서 변경 불가
    fun add(word: String) {
        counter += word.length // 내부에서만 가능
    }
}