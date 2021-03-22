//import java.lang.IllegalArgumentException
//
//interface Expr
//class Num(val value: Int): Expr
//class Sum(val left: Expr, var right: Expr) : Expr
//
//fun eval(e: Expr) : Int =
//    when(e) {
//        is Num -> e.value
//        is Sum -> eval(e.left) + eval(e.right)
//        else -> throw IllegalArgumentException("Unknown") // Default 를 없애려면
//    }

import java.lang.IllegalArgumentException

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, var right: Expr) : Expr()
}

//Expr에 Num/Sum 외에 다른 확장 클래스가 없다는 것을 보장함 (sealed 했기 때문)
fun eval(e: Expr) : Int =
    when(e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.left) + eval(e.right)
    }