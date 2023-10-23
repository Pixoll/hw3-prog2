import backend.Moneda100;
import backend.Moneda1000;
import backend.Moneda1500;
import backend.Moneda500;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonedaTest {
    private Moneda100 m100;
    private Moneda500 m500;
    private Moneda1000 m1000;
    private Moneda1500 m1500;

    @BeforeEach
    void setUp() {
        this.m100 = new Moneda100();
        this.m500 = new Moneda500();
        this.m1000 = new Moneda1000();
        this.m1500 = new Moneda1500();
    }

    @Test
    @DisplayName("Revisar que .getSerie() no es nulo")
    void getSerieNotNull() {
        assertNotNull(this.m100.getSerie());
        assertNotNull(this.m500.getSerie());
        assertNotNull(this.m1000.getSerie());
        assertNotNull(this.m1500.getSerie());
    }

    @Test
    @DisplayName("Revisar que .getSerie() es igual a la moneda")
    void getSerieEqualsSelf() {
        assertEquals(this.m100, this.m100.getSerie());
        assertEquals(this.m500, this.m500.getSerie());
        assertEquals(this.m1000, this.m1000.getSerie());
        assertEquals(this.m1500, this.m1500.getSerie());
    }

    @Test
    @DisplayName("Revisar que .getSerie() no es igual a una diferente")
    void getSerieNotEqualsOther() {
        assertNotEquals(this.m100.getSerie(), this.m500.getSerie());
        assertNotEquals(this.m100.getSerie(), this.m1000.getSerie());
        assertNotEquals(this.m100.getSerie(), this.m1500.getSerie());
        assertNotEquals(this.m500.getSerie(), this.m1000.getSerie());
        assertNotEquals(this.m500.getSerie(), this.m1500.getSerie());
        assertNotEquals(this.m1000.getSerie(), this.m1500.getSerie());
    }

    @Test
    @DisplayName("Revisar el valor de la moneda")
    void getValor() {
        assertEquals(100, this.m100.getValor());
        assertEquals(500, this.m500.getValor());
        assertEquals(1000, this.m1000.getValor());
        assertEquals(1500, this.m1500.getValor());
    }

    @Test
    @DisplayName("Comparar backend.Moneda100 con el resto")
    void m100CompareTo() {
        assertEquals(0, this.m100.compareTo(this.m100));
        assertTrue(this.m100.compareTo(this.m500) < 0);
        assertTrue(this.m100.compareTo(this.m1000) < 0);
        assertTrue(this.m100.compareTo(this.m1500) < 0);
    }

    @Test
    @DisplayName("Comparar backend.Moneda500 con el resto")
    void m500CompareTo() {
        assertTrue(this.m500.compareTo(this.m100) > 0);
        assertEquals(0, this.m500.compareTo(this.m500));
        assertTrue(this.m500.compareTo(this.m1000) < 0);
        assertTrue(this.m500.compareTo(this.m1500) < 0);
    }

    @Test
    @DisplayName("Comparar backend.Moneda1000 con el resto")
    void m1000CompareTo() {
        assertTrue(this.m1000.compareTo(this.m100) > 0);
        assertTrue(this.m1000.compareTo(this.m500) > 0);
        assertEquals(0, this.m1000.compareTo(this.m1000));
        assertTrue(this.m1000.compareTo(this.m1500) < 0);
    }

    @Test
    @DisplayName("Comparar backend.Moneda1500 con el resto")
    void m1500CompareTo() {
        assertTrue(this.m1500.compareTo(this.m100) > 0);
        assertTrue(this.m1500.compareTo(this.m500) > 0);
        assertTrue(this.m1500.compareTo(this.m1000) > 0);
        assertEquals(0, this.m1500.compareTo(this.m1500));
    }
}
