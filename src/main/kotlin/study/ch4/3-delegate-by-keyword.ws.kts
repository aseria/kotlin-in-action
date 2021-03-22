//decorator 패턴 대신 클래스에 해당 함수를 전달

//decorator 패턴을 ㅡㅆ려면 아래와 같이 기본 데코레이터 뼈대 함수를 만들어야함
class DelegatingCollectionDecorator<T> : Collection<T> {
    private val innerList = arrayListOf<T>()
    override val size : Int get() = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T) = innerList.contains(element)
    override fun iterator() : Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>) : Boolean = innerList.containsAll(elements)
}

// .아래 by innerList 를 통해 내부 isEmpty .같은 함수를 위임해서 사용할 수 있음
class DelegatingCollection<T> (
    innerList : Collection<T> = arrayListOf()
        ) : Collection<T> by innerList'

//아래와 같은 예제를 의존관계 없이 구현할 수 있음

class CountingSet<T> (
    val innerSet : MutableCollection<T> = HashSet<T>()
        ) : MutableCollection<T> by innerSet
{
    var objectAdded = 0
    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}