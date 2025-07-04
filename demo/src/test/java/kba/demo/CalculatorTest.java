package kba.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    // zu simulierende Mocks
    private MathService mathService;
    private CalculatorLogger logger;

    // Die zu testende Klasse, die mit dem Mock arbeitet
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        mathService = mock(MathService.class); // Mock-Erzeugung
        logger = mock(CalculatorLogger.class);
        calculator = new Calculator(mathService, logger); // Verwendung des Mocks
    }

    @Test
    void testCalculateSum_withStub() {
        // Stub: Vorgabe des Rückgabewerts
        when(mathService.add(5, 3)).thenReturn(999);

        // Testausführung
        int result = calculator.calculateSum(5, 3);

        // Prüfen ob Result der Vorgabe entspricht
        assertEquals(999, result);

    }

    @Test
    void testCalculateProduct_withStub() {
        // Stub für multiply
        when(mathService.multiply(4, 2)).thenReturn(42);

        int result = calculator.calculateProduct(4, 2);

        assertEquals(42, result);

    }

    @Test
    void testLoggingWithIndirectOutput() {
        // Stub
        when(mathService.add(2, 3)).thenReturn(5);

        calculator.calculateSum(2, 3);

        // Test des indirekten Output (Interaktion mit dem Logger)
        verify(logger).log("Sum of 2 and 3 is 5");
    }

    @Test
    void testNoInteraction() {
        verifyNoInteractions(mathService);
    }
}