
// 자바 함수형 인터페이스도 코틀린에서 람다로 사용 가능 (이건 자바 8에서도 변환해주는 듯?)
/** button.setOnClickListener(new OnClickListener() {
@Override
public void onClick(View v) {

}
}
-->
button.setOnClickListener { view -> }
 **/

//이런 함수형 인터페이스를 람다로 바꾸는 것은 파라미터로도 가능
// void postponeComputation(int delay, Runnable computation);
// Runnable 은 구현해야할 함수가 하나인 함수형 인터페이스 이므로
// postponeComputation(1000) { println(42) } 와 같이 람다식으로 넘겨서 가능
// 이것을 SAM 생성자라고 하는듯 (Single Abstract Method)

