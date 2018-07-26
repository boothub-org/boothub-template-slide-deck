package {{targetPackage}}

fun main(args: Array<String>) {
    // tag::decl[]
    val x = 6
    val y = 2
    // end::decl[]
    // tag::add[]
    println("x + y = ${x + y}")  // Addition: x + y = 8
    // end::add[]
    // tag::sub[]
    println("x - y = ${x - y}")  // Subtraction: x - y = 4
    // end::sub[]
    // tag::mult[]
    println("x * y = ${x * y}")  // Multiplication x * y = 12
    // end::mult[]
    // tag::div[]
    println("x / y = ${x / y}")  // Division: x / y = 3
    // end::div[]
    // tag::mod[]
    println("x % y = ${x % y}")  // Remainder: x % y = 0
    // end::mod[]
}
