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

//declarando classes genericas:


