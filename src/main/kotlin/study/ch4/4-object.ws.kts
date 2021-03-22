import java.io.File

class PersonMock

object Payroll {
    val allEmployee = arrayListOf<PersonMock>()
    fun calculateSalary() {
        allEmployee.forEach{ println(it) }
    }
}

Payroll.allEmployee // 이런식으로 활용 가능

// 스태틱한 유틸 함수
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File?, o2: File?): Int {
        return o2?.let { o1?.path?.compareTo(it.parent, ignoreCase = true) } ?: 0
    }
}

val files = listOf(File("/Z"), File("/M"))
files.sortedWith(CaseInsensitiveFileComparator) // 이렇게 가능

class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}
Person.NameComparator // 이렇게 활용 가능