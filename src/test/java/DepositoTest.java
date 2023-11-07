import backend.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DepositoTest {
    @Test
    @DisplayName("Añadir y remover productos de un deposito")
    void addGetProducto() {
        Deposito<Producto> deposito = new Deposito<>();

        CocaCola bCocaCola = new CocaCola(1);
        Sprite bSprite = new Sprite(2);
        Fanta bFanta = new Fanta(3);
        Snickers dSnickers = new Snickers(4);
        Super8 dSuper8 = new Super8(5);

        deposito.add(bCocaCola);
        deposito.add(bSprite);
        deposito.add(bFanta);
        deposito.add(dSnickers);
        deposito.add(dSuper8);

        assertEquals(bCocaCola, deposito.get());
        assertEquals(bSprite, deposito.get());
        assertEquals(bFanta, deposito.get());
        assertEquals(dSnickers, deposito.get());
        assertEquals(dSuper8, deposito.get());
        assertNull(deposito.get());

        deposito.add(dSnickers);
        assertEquals(dSnickers, deposito.get());
        assertNull(deposito.get());
    }

    @Test
    @DisplayName("Añadir y remover monedas de un deposito")
    void addGetMoneda() {
        Deposito<Moneda> deposito = new Deposito<>();

        Moneda100 m100 = new Moneda100();
        Moneda500 m500 = new Moneda500();
        Moneda1000 m1000 = new Moneda1000();
        Moneda1500 m1500 = new Moneda1500();

        deposito.add(m100);
        deposito.add(m500);
        deposito.add(m1000);
        deposito.add(m1500);

        assertEquals(m100, deposito.get());
        assertEquals(m500, deposito.get());
        assertEquals(m1000, deposito.get());
        assertEquals(m1500, deposito.get());
        assertNull(deposito.get());

        deposito.add(m500);
        assertEquals(m500, deposito.get());
        assertNull(deposito.get());
    }
}