import java.lang.StringBuilder
val test = listOf("a","b","c")

//named parameter
fun <T> joinToString(
    collections: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)
    for((index, element) in collections.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
println(joinToString(test, separator = ",", prefix = "(", postfix = ")"))

//default parameter
fun <T> joinToStringDefault(
    collections: Collection<T>,
    separator: String = ",",
    prefix: String = "(",
    postfix: String = ")"
): String {
    val result = StringBuilder(prefix)
    for((index, element) in collections.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

println(joinToStringDefault(test))


