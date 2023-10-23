import backend.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompradorTest {
    private Expendedor expendedor;
    private Moneda100 m100;
    private Moneda500 m500;
    private Moneda1000 m1000;
    private Moneda1500 m1500;
    private CocaCola cocaCola;
    private Fanta fanta;
    private Sprite sprite;
    private Snickers snickers;
    private Super8 super8;

    @BeforeEach
    void setUp() {
        this.expendedor = new Expendedor(1);
        this.m100 = new Moneda100();
        this.m500 = new Moneda500();
        this.m1000 = new Moneda1000();
        this.m1500 = new Moneda1500();
        this.cocaCola = new CocaCola(1);
        this.fanta = new Fanta(2);
        this.sprite = new Sprite(3);
        this.snickers = new Snickers(4);
        this.super8 = new Super8(5);
    }

    @Test
    @DisplayName("Comprar todo y revisar vuelto")
    void cuantoVuelto() {
        try {
            assertEquals(0, this.comprar(TipoProductos.COCA_COLA, this.m1000).cuantoVuelto());
            assertEquals(200, this.comprar(TipoProductos.FANTA, this.m1000).cuantoVuelto());
            assertEquals(100, this.comprar(TipoProductos.SPRITE, this.m1000).cuantoVuelto());
            assertEquals(300, this.comprar(TipoProductos.SNICKERS, this.m1500).cuantoVuelto());
            assertEquals(400, this.comprar(TipoProductos.SUPER8, this.m500).cuantoVuelto());
        } catch (Exception error) {
            fail("No deberían haber excepciones. Obtuvo " + error.getClass().getName());
        }
    }

    @Test
    @DisplayName("Comprar todo y revisar qué compró")
    void queCompraste() {
        try {
            assertEquals(this.cocaCola.beber(), this.comprar(TipoProductos.COCA_COLA, this.m1500).queCompraste());
            assertEquals(this.fanta.beber(), this.comprar(TipoProductos.FANTA, this.m1500).queCompraste());
            assertEquals(this.sprite.beber(), this.comprar(TipoProductos.SPRITE, this.m1500).queCompraste());
            assertEquals(this.snickers.comer(), this.comprar(TipoProductos.SNICKERS, this.m1500).queCompraste());
            assertEquals(this.super8.comer(), this.comprar(TipoProductos.SUPER8, this.m1500).queCompraste());
        } catch (Exception error) {
            fail("No deberían haber excepciones. Obtuvo " + error.getClass().getName());
        }
    }

    @Test
    @DisplayName("Comprar todo sin dinero")
    void comprarSinMoneda() {
        try {
            this.comprar(TipoProductos.COCA_COLA, null);
            fail("Se esperaba backend.PagoIncorrectoException");
            this.comprar(TipoProductos.FANTA, null);
            fail("Se esperaba backend.PagoIncorrectoException");
            this.comprar(TipoProductos.SPRITE, null);
            fail("Se esperaba backend.PagoIncorrectoException");
            this.comprar(TipoProductos.SNICKERS, null);
            fail("Se esperaba backend.PagoIncorrectoException");
            this.comprar(TipoProductos.SUPER8, null);
            fail("Se esperaba backend.PagoIncorrectoException");
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Comprar todo sin dinero suficiente")
    void comprarConInsuficiente() {
        try {
            this.comprar(TipoProductos.COCA_COLA, this.m100);
            fail("Se esperaba backend.PagoInsuficienteException");
            this.comprar(TipoProductos.FANTA, this.m100);
            fail("Se esperaba backend.PagoInsuficienteException");
            this.comprar(TipoProductos.SPRITE, this.m100);
            fail("Se esperaba backend.PagoInsuficienteException");
            this.comprar(TipoProductos.SNICKERS, this.m100);
            fail("Se esperaba backend.PagoInsuficienteException");
            // Super 8 se puede comprar con backend.Moneda100, así que no se incluye
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Comprar todo cuando no hay stock")
    void comprarSinStock() {
        expendedor = new Expendedor(0);

        try {
            this.comprar(TipoProductos.COCA_COLA, this.m1500);
            fail("Se esperaba backend.NoHayProductoException");
            this.comprar(TipoProductos.FANTA, this.m1500);
            fail("Se esperaba backend.NoHayProductoException");
            this.comprar(TipoProductos.SPRITE, this.m1500);
            fail("Se esperaba backend.NoHayProductoException");
            this.comprar(TipoProductos.SNICKERS, this.m1500);
            fail("Se esperaba backend.NoHayProductoException");
            this.comprar(TipoProductos.SUPER8, this.m1500);
            fail("Se esperaba backend.NoHayProductoException");
        } catch (Exception ignored) {
        }
    }

    private Comprador comprar(TipoProductos producto, Moneda pago)
            throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {
        return new Comprador(producto, pago, expendedor);
    }
}