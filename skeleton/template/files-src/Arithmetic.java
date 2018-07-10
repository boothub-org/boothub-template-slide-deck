package {{targetPackage}};

public class Arithmetic {
    public static void main(String[] args) {
        // tag::decl[]
        int x = 6;
        int y = 2;
        // end::decl[]
        // tag::add[]
        System.out.println("x + y = " + (x + y));  // Addition: x + y = 8
        // end::add[]
        // tag::sub[]
        System.out.println("x - y = " + (x - y));  // Subtraction: x - y = 4
        // end::sub[]
        // tag::mult[]
        System.out.println("x * y = " + (x * y));  // Multiplication x * y = 12
        // end::mult[]
        // tag::div[]
        System.out.println("x / y = " + (x / y));  // Division: x / y = 3
        // end::div[]
        // tag::mod[]
        System.out.println("x % y = " + (x % y));  // Remainder: x % y = 0
        // end::mod[]
    }
}
