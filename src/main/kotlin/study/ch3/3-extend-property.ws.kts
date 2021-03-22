val String.lastChar : Char
    get() = get(length -1)

val str = "abcde"

println(str.lastChar)

var StringBuilder.lastChar: Char
    get() = get(length -1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

val sb = StringBuilder("kotlinLast?")
sb.lastChar = '!'

println(sb.toString())