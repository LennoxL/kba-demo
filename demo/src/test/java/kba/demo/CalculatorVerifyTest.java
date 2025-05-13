package kba.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)

@ExtendWith(MockitoExtension.class)
class CalculatorVerifyTest {

    // Mock-Objekt für MathService
    @Mock
    private MathService mathService;

    // Mock-Objekt für CalculatorLogger
    @Mock
    private CalculatorLogger logger;

    // Mocks werden injiziert
    @InjectMocks
    private Calculator calculator;

    // Test 1: Verifizierung, dass die Methode add() aufgerufen wird
    @Test
    void verifyAddTest() {
        // Stub von add(3,7)
        when(mathService.add(3, 7)).thenReturn(10);

        // calculateSum aufrufen
        int result = calculator.calculateSum(3, 7);

        // Rückgabewert stimmt mit Stub überein
        assertEquals(10, result);

        // Verify: mathService.add wurde mit den Parametern (3,7) aufgerufen
        verify(mathService).add(3, 7);
    }

    // Test 2: Verifizierung ohne Rückgabe / assertEquals
    @Test
    void verifyAddWithoutAssertEqualsTest() {
        // Stub von multiply(4,5)
        when(mathService.multiply(4, 5)).thenReturn(20);

        // Act: Führe die Methode calculateProduct aus
        int result = calculator.calculateProduct(4, 5);

        // Verify: mathService.multiply wurde mit (4,5) aufgerufen
        verify(mathService).multiply(4, 5);
    }

    // Test 3: Verifizierung der Häufigkeit der Aufrufe
    @Test
    void verifyAddTimesTest() {
        // Stub von multiply(4,5)
        when(mathService.multiply(4, 5)).thenReturn(20);

        // Act: Führe die Methode calculateProduct aus
        int result = calculator.calculateProduct(4, 5);

        // Verify: mathService.multiply wurde mindestens einmal mit (4,5) aufgerufen
        verify(mathService, times(1)).multiply(4, 5);
    }


    // Test 4: Verifizierung von keinen weiteren Aufrufen
    @Test
    void verifyNoMoreInteractionsTest() {
        // Stub von add(3,7)
        when(mathService.add(3, 7)).thenReturn(10);

        // calculateSum aufrufen
        int result = calculator.calculateSum(3, 7);

        // Verify: mathService.add wurde mit aufgerufen
        verify(mathService).add(3, 7);

        // Keine weiteren Interaktionen
        verifyNoMoreInteractions(mathService);
    }


    // Test 5: Verifizierung der Reihenfolge der Aufrufe
    @Test
    void verifyOrderTest() {
        // Stub von add(3,7) und multiply(4,5)
        when(mathService.add(3, 7)).thenReturn(10);
        when(mathService.multiply(4, 5)).thenReturn(20);

        // erst calculateSum aufrufen, dann calculateProduct
        int sum = calculator.calculateSum(3, 7);
        int product = calculator.calculateProduct(4, 5);

        // Überprüfe die Reihenfolge
        InOrder inOrder = inOrder(mathService);
        inOrder.verify(mathService).add(3, 7);
        inOrder.verify(mathService).multiply(4, 5);
    }



    // Test 6: ArgumentMatcher für beliebigen Integer
    @Test
    void verifyArgumentMatcherTest() {
        // Stubbe add(anyInt(), anyInt()) auf 10
        when(mathService.add(3, 7)).thenReturn(10);

        // aufrufen von calculateSum
        int result = calculator.calculateSum(10, 32);

        // Überprüfen das 2 beliebige Integer übergeben wurden
        verify(mathService).add(anyInt(), anyInt());
    }

    // Test 7: ArgumentCaptor um Werte zu identifiieren
    @Captor
    private ArgumentCaptor<Integer> captorA;
    @Captor
    private ArgumentCaptor<Integer> captorB;

    @Test
    void verifyArgumentCaptorTest() {
        // Stub von add(3,7)
        when(mathService.add(3, 7)).thenReturn(10);

        // aufrufen von calculateSum
        int result = calculator.calculateSum(7, 13);

        // Capture: Fange die übergebenen Argumente ab
        verify(mathService).add(captorA.capture(), captorB.capture());

        System.out.println("captorA: " + captorA.getValue());
        System.out.println("captorB: " + captorB.getValue());

    }

}
