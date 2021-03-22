//interface
interface Clickable {
    fun click()
    fun showOff() = println("click")
}

interface Focusable  {

    fun setFocus(b: Boolean) =
        println("I ${if(b) "got" else "lost"} focus.")
    fun showOff() = println("focusable")
}

//class Button : Clickable, Focusable
// --> showOff 이 두 인터페이스ㅔ 모두 구현되어 있어 오류
class Button : Clickable, Focusable {
    override fun click() {
        println("Button is clicked")
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
val btn = Button()
btn.click()
btn.showOff()

// 클래스든 내부 함수든 모두 override 하려면 open 클래스가 필요하다.
// override 함수는 기본적으로 open
// final override fun ...   : 상속한 클래스는 이 함수를 override 불가능하다.


//가시성은 기본적으로 public