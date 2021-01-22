// Exemplos de código sobre generics do capítulo 9 do livro Kotlin em ação

//inferencia de tipo explicita:

val readers : MutableList<String> = mutableListOf()
//ou
val readers = mutableListOf<String>()


//Funções e propriedades genericas


//Declarando função generica
fun <T> List<T>.slice(indice : IntRange) : List<T>

//Chamando função generica
val letters = ('a' .. 'z').toList()
println(letters.slice<Char>(0 .. 2))// saida: [a,b,c]
println(letters.slice(10..13)) // saida: [k,l,m,n]

//Chamando função de alta ordem genérica
val authors = listOf("Dmitry","Svetlana")
val readers = mutableListOf<String>(/* ... */)

fun <T> List<T>.filter(predicate : (T) -> Boolean) : List<T>

readers.filter { it !in authors}

//usando generics em propriedades de extensão
val <T> List<T>.penultimate : T
	get() = this[size - 2]

val <T> List<T>.last : T
	get() = this[size -1]

	var list = listOf(1,2,3,4)

println(list.penultimate) // saida: 3
println(list.last) //saida: 4

//declarando classes ou interfaces genericas:
interface List<T> {
	//o operador get() é o equivalente ao [] para pegar o elemento no index x de uma lista
	operator fun get(index: Int) : T

}
//classes que erdam de tipo generico ou implementam uma interface generica
// devem fornecer um argumento de tipo para o parametro generico do tipo-base
class StringList : List<String>{
		override fun get(index: Int) : String = ...
}
class ArrayList<T> : List<T> {
	override fun get(index: Int) : T = ...
}

//Uma classe pode se referir a ela mesma como parametro de tipo:
interface Comparable<T> {
	fun compareTo(other : T) : Int
}
class String : Comparable<String> {
	override fun compareTo(other: String) : Int = /* ... */
}

//Restrições para parâmetros de tipo
fun <T: Number> oneHalf(value : T) : Double {
	return value.toDouble() / 2.0
}

//Função para comparar o máximo entre dois itens:
fun <T: Comparable<T>> max(first: T,second : T) : T {
	return if(first > second) first else second
}

//Especificando varias retrições para um parâmetro de tipo:
// nesse exemplo T precisa Implementar as interfaces CharSequence e Appendable
fun <T> ensureTrailingPeriod(seq: T) where T: CharSequence,T: Appendable {
	if(!seq.endsWIth(".") //Função de extensão presente para CharSequence) {
		sec.append(".") // metodo presente em Appendable
	}
}

//Garatindo que os parametros de tipo sejam não null

// por padrão os parametros de tipo sempre seram nullable, mesmo não tendo "?"
// para contornar isso basta usar Any ou qualquer tipo não nullable como limite superior:
class Processor<T : Any> {
	fun process(value : T) {
		value.hashCode()
	}
}

//Em tempo de execução as informações sobre o tipo de uma instancia não são mantidos
// Caso necessario, podemos contornar isso usando reificando os tipos:

//para verificar por exemplo em tempo de execução de uma variavel é do tipo list:

//Star Projection
if(value is List<*>) {
	/* ... */
}

//Usando um cast de tipo com tipo genérico:
fun printSum(c: Collection<*>) {
	val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
	println(intList.sum())
}