package kba.demo;

public class Main {
    public static void main(String[] args) {
        MathService mathService = new MathService() {

            public int add(int a, int b) {
                return a + b;
            }

            public int multiply(int a, int b) {
                return a * b;
            }
        };

        Calculator calculator = new Calculator(mathService);

        int sum = calculator.calculateSum(3, 7);
        int product = calculator.calculateProduct(4, 5);

        System.out.println("Summe: " + sum);
        System.out.println("Produkt: " + product);
    }
}