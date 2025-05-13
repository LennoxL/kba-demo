package kba.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculatorAnnotationsTest {

    // zu simulierende Mocks
    @Mock
    MathService mathService;

    @Mock
    CalculatorLogger logger;

    // Die zu testende Klasse, die mit dem Mock arbeitet
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(mathService, logger);
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