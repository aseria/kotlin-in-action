
class A {
    companion object {
        fun bar() {
            println("Companion object")
        }
    }
}

A.bar() //컴패니언 객체 호출

//Companion Object 는 Private 매소드를 호출 가능
// 따라서 private 한 construcotr 를 통해 factory 메소드를 생성하는 것이 가능하다
object FacebookIdGetter {
    fun getNickName(facebookId: Int): String {
        return facebookId.toString()
    }
}
class UserMock {
    val nickName : String

    constructor(email: String) {
        nickName = email.substringBefore('@')
    }
    constructor(facebookId: Int) {
        nickName = FacebookIdGetter.getNickName(facebookId)
    }
}

// 아래처럼 팩토리 처럼 개발이 가능하다.
class User private constructor(val nickName: String) {
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newFbUser(facebookId: Int) = User(FacebookIdGetter.getNickName(facebookId))
    }
}
val subUser = User.newSubscribingUser("asdasd@asdasd")
println(subUser.nickName)

// 동반객체가 있으면
class Person(val name: String) {
    companion object Loader {
        fun fromJson(jsonText: String) : Person = Person(jsonText.substringBefore('}'))
    }
}

Person.Loader.fromJson("{id: 123}")
Person.fromJson("{id: 123}") // 굳이 Loader 를 붙이지 않아도 가능



// 동반객체에서 인터페이스 구현하기
interface JSONFactory<T> {
    fun fromJson(jsonText: String) : T
}

class Animal(val nickname: String) {
    companion object : JSONFactory<Animal> {
        override fun fromJson(jsonText: String): Animal {
            return Animal(jsonText.substring(1..4))
        }
    }
}
fun <T> loadFromJSON(factory: JSONFactory<T>) : T {
    return factory.fromJson("asdasd")
}

loadFromJSON(Animal) // 내부에 CompanionObject 가 JSONFactory 기 때문에 이렇게 넘길 수 있음



// 동반객체도 확장함수로 확장 가능 
class Domain(val domainName: String) {
    companion object {

    }
}

fun Domain.Companion.fromJson(json: String) : Domain { // 도메인 Companion 도 확장 가능
    return Domain(json.substring(1..3))
}
val d = Domain.fromJson("{123124: 1231}") 
