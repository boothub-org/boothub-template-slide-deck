package {{targetPackage}}

class Hello2 {
    // tag::main[]
    static void main(String[] args) {
        String name = args ? args[0] : 'world'
        println "Hello, $name!"
    }
    // end::main[]
}
