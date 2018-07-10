package {{targetPackage}};

public class Hello2 {
    // tag::main[]
    public static void main(String[] args) {
        String name = (args.length > 0) ? args[0] : "world";
        System.out.printf("Hello, %s!\n", name);
    }
    // end::main[]
}
