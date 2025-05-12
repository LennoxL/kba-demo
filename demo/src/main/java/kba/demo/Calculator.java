package kba.demo;

public class Calculator{

    private final MathService mathService;
    private final CalculatorLogger logger;


    public Calculator(MathService mathService, CalculatorLogger logger) {
        this.mathService = mathService;
        this.logger = logger;
    }

    public int calculateSum(int a, int b) {
        int result = mathService.add(a, b);
        logger.log("Sum of " + a + " and " + b + " is " + result);
        return result;
    }

    public int calculateProduct(int a, int b){
        int result = mathService.multiply(a, b);
        logger.log("Product of " + a + " and " + b + " is " + result);
        return result;
    }
}