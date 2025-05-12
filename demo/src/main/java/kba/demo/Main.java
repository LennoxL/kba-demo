package kba.demo;

public class Main {
    public static void main(String[] args) {
        CalculatorLogger logger = message -> System.out.println("test123");
        MathService mathService = new MathService() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
            @Override
            public int multiply(int a, int b) {
                return a * b;
            }
        };

        Calculator calculator = new Calculator(mathService, logger);

        int sum = calculator.calculateSum(3, 7);
        int product = calculator.calculateProduct(4, 5);

        System.out.println("Summe: " + sum);
        System.out.println("Produkt: " + product);
    }
}