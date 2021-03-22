package study.ch3

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if(user.name.isEmpty()) {
        throw IllegalArgumentException("Name is Empty")
    }
    if(user.address.isEmpty()) {
        throw IllegalArgumentException("Address is Empty")
    }
}

fun saveUser2(user: User) {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("${user.id} 's $fieldName is Empty") // 함수 내부 변수에 접근 가능 (클로저?)
        }
    }
    validate(user.name, "User")
    validate(user.address, "Address")
}

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("$id 's $fieldName is Empty") // 함수 내부 변수에 접근 가능 (클로저?)
        }
    }
    validate(name,"Name")
    validate(address,"Address")
}

fun saveUser3(user: User) {
    user.validateBeforeSave()
}

println(saveUser3(User(1,"",""))) // IllegraArgumentExceptino 발생
