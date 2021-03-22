//Kotlin 의 원시타입(primitive type)은 컴파일타임에 알아서 맞추어 JavaTyp[e 으로 변환됨

// 지원하는 타입 종류
// Byte, Short, Int, Long
// Float, Double
// Char
// Boolean

//Boolean? 와 같이 nullable한 타입은 자바의 wrapper 타입으로 변환됨

//원시타입을 사용하는 서드파티 컬렉션 라이브러리
// http://trove4j.sourceforge.net/html/overview.html

//숫자 변환은 수동으로 해야함
val i  = 1 // Int

val l: Long = i.toLong()
