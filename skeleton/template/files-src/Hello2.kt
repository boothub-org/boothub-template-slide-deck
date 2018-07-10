package {{targetPackage}}

// tag::main[]
fun main(args: Array<String>) {
    val name = if(args.size > 0) args[0] else "world"
    println("Hello, $name!")
}
// end::main[]
