package kba.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalculatorSpyTest {


    private CalculatorLogger logger;
    private Calculator calculator;

    private MathService mathServiceSpy;

    @BeforeEach
    void setUp() {
        // 1. Erstelle eine „echte“ MathService-Implementierung
        MathService realService = new MathService() {
            @Override
            public int add(int a, int b) {
                return a + b;    // echte Addition
            }
            @Override
            public int multiply(int a, int b) {
                return a * b;    // echte Multiplikation
            }
        };

        // 2. Spy erzeugen
        mathServiceSpy = spy(realService);

        // 3. Mock des Loggers
        logger = mock(CalculatorLogger.class);

        // 4. Calculator mit dem Spy und dem Mock initialisieren
        calculator = new Calculator(mathServiceSpy, logger);
    }

    // Test 1: Stubben der add-Methode
    @Test
    void spyStubTest() {
        // Stub von add(5,3)
        doReturn(999).when(mathServiceSpy).add(5, 3);

        // Aufruf der calculateSum-Methode, die Spy-Methode add() verwendet
        int result = calculator.calculateSum(5, 3);
        assertEquals(999, result);

    }

    // Test 2: Stubben der add-Methode und Aufruf der echten Methode multiply
    @Test
    void spyStubAndRealTest() {
        // Stub von add(5,3)
        doReturn(999).when(mathServiceSpy).add(5, 3);

        // Aufruf von calculateSum und calculateProduct
        int resultSum = calculator.calculateSum(5, 3);
        int resultMultiply = calculator.calculateProduct(5, 10);

        // Prüfe, dass die Stub-Werte und die echten Werte korrekt sind
        assertEquals(999, resultSum);
        assertEquals(50, resultMultiply);
    }
}
