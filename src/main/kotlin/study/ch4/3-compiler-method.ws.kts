
class Client(val name: String, val postalCode: Int) {
    override fun toString(): String {
        return "Client(name=$name, postaclCode=$postalCode)"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }
}

// 이것이 매우 편함
data class DataClient(val name: String, val postalCode: Int)

val client1 = DataClient("Kim", 123)
val client2 = client1.copy(name = "Lee")

println(client1)
println(client2)

