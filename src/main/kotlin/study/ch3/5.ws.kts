/**문자열 정규식**/
val testString = "Hello.Kotlin-123"

//기본적으로 정규식이 아님
testString.split(".","-")
testString.split(".")
testString.split("[.\\-]".toRegex())

//복잡한 정규식
val pathStr = "/home/users/kotlin/study.kt"

fun parsePath(path: String) {
    val dir = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val ext = fullName.substringAfterLast(".")

    println("Dir : $dir, fileName $fileName , ext : $ext")
}
parsePath(pathStr)

fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    matchResult?.let {
        val (dir, fileName, ext) = it.destructured
        println("Dir : $dir, fileName $fileName , ext : $ext")
    }
}
parsePathRegex(pathStr)

