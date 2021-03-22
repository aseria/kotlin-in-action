
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    return result.toString()
}
alphabet()

/**  with 사용하기 **/
fun alphabetWith(): String {
    return with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        this.toString()
    }
}
alphabetWith() // 자원해제해주기 되게 좋겠다..

//간략한 버전

fun alphabetWithSimple() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    toString()
}
alphabetWithSimple()

/** Apply **/
//객체의 인스터스를 만들면서 프로퍼티 중 일부를 초기화 하거나 변경이 필요할 때 상요
fun alphabetApply() = StringBuilder().apply { // this 가 넘어감
    for (letter in 'A'..'Z') {
        append(letter)
    }
}.toString() // 그래서 여기서 String 으로 바꿔줘야함
alphabetApply()

fun alphabetBuildString() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
}
alphabetBuildString()