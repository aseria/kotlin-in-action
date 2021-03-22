
fun <T> printHashCode(t: T) {
    println(t?.hashCode()) // nullable 로 추론
}

// T는 Any이보다는 높아야함...
fun <T:Any> printHashCodeNotNull(t: T) {
    println(t.hashCode()) // not null 타입으로 추론됨
}

//자바에서 오는 플랫폼타입은 ! 타입임..
// 아래와 같이 사용자가 선택해서 가능
// val s: String? = getStringFromJava()
// val s: String = getStringFromJava()