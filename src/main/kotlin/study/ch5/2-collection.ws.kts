data class Person(val name: String, val age: Int)

val people = listOf(Person("Kim", 32), Person("Lee", 24), Person("Park", 24))
//filter map
people.filter { it.age > 30 }
people.map { it.copy(name = it.name + "Mapped")}

//all any count find
people.all { it.age > 10}
people.any { it.age < 30}
people.count { it.name == "Kim"} // people.filter { it.name == "Kim" }.size 를 상요하지 말것
people.find { it.name == "Kim"}

val moreThan30Filter = { p: Person -> p.age > 30 }
val ageFilter = { age: Int -> { p:Person -> p.age > age}}

people.filter(moreThan30Filter) //
people.filter(ageFilter(30)) // hof 사용해봄 !

// groupby

people.groupBy { it.age } // Map 현태로 반환

// flatMap flatten

val strings = listOf("abc", "def")
strings.flatMap { it.toList() } // string 도 char 의 리스트이므로 풀린다 ㅎ

