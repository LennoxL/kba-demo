package kba.demo;

public class Calculator {

    private final MathService mathService;

    public Calculator(MathService mathService) {
        this.mathService = mathService;
    }

    public int calculateSum(int a, int b) {
        return mathService.add(a, b);
    }

    public int calculateProduct(int a, int b) {
        return mathService.multiply(a, b);
    }
}