val strings = listOf("first","second","third")
println(strings.maxOrNull())
strings.last()

/** 가변 인자 **/
val newList = listOf("zero", *strings.toTypedArray())
// * 는 배열 일때만, 파라미터를 풀어서 전달
// 리스트는 안됨
// listOf("zero", *strings) --> Error
println(newList)

/** 중위 호출 **/

infix fun Any.to(other:Any) = Pair(this, other)
1 to 2 // (1,2)

val (x, y) = 1 to 2
println("$x + $y")

