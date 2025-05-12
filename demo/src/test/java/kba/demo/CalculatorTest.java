package kba.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    // 👉 1. MOCK: Wir erzeugen ein simuliertes MathService-Objekt
    @Mock
    private MathService mathService;

    // Die zu testende Klasse, die mit dem Mock arbeitet
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        mathService = mock(MathService.class); // Mock-Erzeugung
        calculator = new Calculator(mathService); // Verwendung des Mocks
    }

    @Test
    void testCalculateSum_withStub() {
        // 👉 2. STUB: Wir sagen dem Mock, was er zurückgeben soll
        when(mathService.add(5, 3)).thenReturn(999);

        // Testausführung
        int result = calculator.calculateSum(5, 3);

        // 👉 3. ASSERT: Ergebnis ist genau das, was der Stub vorgibt
        assertEquals(999, result);

        // 👉 4. VERIFY: Prüft, ob die Methode mit diesen Argumenten aufgerufen wurde
        verify(mathService).add(5, 3);
    }

    @Test
    void testCalculateProduct_withStub() {
        // Stub für multiply
        when(mathService.multiply(4, 2)).thenReturn(42);

        int result = calculator.calculateProduct(4, 2);

        assertEquals(42, result);

        // Hier prüfen wir, dass der Mock auch wirklich benutzt wurde
        verify(mathService).multiply(4, 2);
    }

    @Test
    void testNoInteraction() {
        // 👉 Hier rufen wir keine Methode auf

        // 👉 Wir prüfen, dass der Mock nicht verwendet wurde
        verifyNoInteractions(mathService);
    }
}