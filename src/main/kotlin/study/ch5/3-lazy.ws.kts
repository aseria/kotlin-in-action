import java.io.File

//map/filter 처럼 이전에 소개한 컬렉션 함수들은 컬렉션을 즉시 생성

//시퀀스를 사용하면 중간 임시 컬렉션을 사용할 수 있다 . (평가 전까지 연산을 보류함)
// 자바 Stream, C# 과 비슷하다는 느낌이네...

data class Person(val name: String, val age: Int)
val people = listOf(Person("Kim", 32), Person("Lee", 24), Person("Park", 24))

people.asSequence()
    .map(Person::name)
    .filter { it.startsWith("P") }
    .toList()

// 전체 연산이 일어나지 않고, 1, 2 만 연산후 결과가 반환되어 효율적이다
listOf(1,2,3,4).asSequence().map { it * it }.find { it > 3}

// map 연산이 모두 끝나고 처리됨
listOf(1,2,3,4).map { it * it }.find { it > 3}

//시퀀스 만들기

val nNumbers = generateSequence(0) { it + 1 } // 1부터 증가되는 시퀀스
val numbersTo100 = nNumbers.takeWhile { it <= 100 }
numbersTo100.sum()

fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.any { it.isHidden }
val f = File("F:\\study\\kotlin-playground\\src")
f.isInsideHiddenDirectory()


