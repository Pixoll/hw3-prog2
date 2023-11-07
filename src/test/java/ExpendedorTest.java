import backend.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpendedorTest {
    private Expendedor expendedor;
    private Moneda100 m100;
    private Moneda500 m500;
    private Moneda1000 m1000;
    private Moneda1500 m1500;

    @BeforeEach
    void setUp() {
        this.expendedor = new Expendedor(1);
        this.m100 = new Moneda100();
        this.m500 = new Moneda500();
        this.m1000 = new Moneda1000();
        this.m1500 = new Moneda1500();
    }

    @Test
    @DisplayName("Comprar todo y revisar el tipo del producto")
    void comprarProductoExitoso() {
        try {
            Producto cocaCola = this.comprar(TipoProductos.COCA_COLA, m1000);
            assertTrue(cocaCola instanceof CocaCola);
            Producto fanta = this.comprar(TipoProductos.FANTA, m1000);
            assertTrue(fanta instanceof Fanta);
            Producto sprite = this.comprar(TipoProductos.SPRITE, m1000);
            assertTrue(sprite instanceof Sprite);
            Producto snickers = this.comprar(TipoProductos.SNICKERS, m1500);
            assertTrue(snickers instanceof Snickers);
            Producto super8 = this.comprar(TipoProductos.SUPER8, m500);
            assertTrue(super8 instanceof Super8);
        } catch (Exception error) {
            fail("No deberían haber excepciones. Obtuvo " + error.getClass().getName());
        }
    }

    @Test
    @DisplayName("Comprar todo sin dinero")
    void comprarProductoSinMoneda() {
        try {
            this.comprar(TipoProductos.COCA_COLA, null);
            fail("Se esperaba PagoIncorrectoException");
            this.comprar(TipoProductos.FANTA, null);
            fail("Se esperaba PagoIncorrectoException");
            this.comprar(TipoProductos.SPRITE, null);
            fail("Se esperaba PagoIncorrectoException");
            this.comprar(TipoProductos.SNICKERS, null);
            fail("Se esperaba PagoIncorrectoException");
            this.comprar(TipoProductos.SUPER8, null);
            fail("Se esperaba PagoIncorrectoException");
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Comprar todo sin dinero suficiente")
    void comprarConInsuficiente() {
        try {
            this.comprar(TipoProductos.COCA_COLA, this.m100);
            fail("Se esperaba PagoInsuficienteException");
            this.comprar(TipoProductos.FANTA, this.m100);
            fail("Se esperaba PagoInsuficienteException");
            this.comprar(TipoProductos.SPRITE, this.m100);
            fail("Se esperaba PagoInsuficienteException");
            this.comprar(TipoProductos.SNICKERS, this.m100);
            fail("Se esperaba PagoInsuficienteException");
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
            fail("Se esperaba NoHayProductoException");
            this.comprar(TipoProductos.FANTA, this.m1500);
            fail("Se esperaba NoHayProductoException");
            this.comprar(TipoProductos.SPRITE, this.m1500);
            fail("Se esperaba NoHayProductoException");
            this.comprar(TipoProductos.SNICKERS, this.m1500);
            fail("Se esperaba NoHayProductoException");
            this.comprar(TipoProductos.SUPER8, this.m1500);
            fail("Se esperaba NoHayProductoException");
        } catch (Exception ignored) {
        }
    }

    @Test
    @DisplayName("Comprar todo y revisar vuelto")
    void getVuelto() {
        try {
            this.comprar(TipoProductos.COCA_COLA, m1000);
            assertEquals(0, this.vuelto());
            this.comprar(TipoProductos.FANTA, m1000);
            assertEquals(200, this.vuelto());
            this.comprar(TipoProductos.SPRITE, m1000);
            assertEquals(100, this.vuelto());
            this.comprar(TipoProductos.SNICKERS, m1500);
            assertEquals(300, this.vuelto());
            this.comprar(TipoProductos.SUPER8, m500);
            assertEquals(400, this.vuelto());
        } catch (Exception error) {
            fail("No deberían haber excepciones. Obtuvo " + error.getClass().getName());
        }
    }

    private Producto comprar(TipoProductos producto, Moneda pago)
            throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {
        expendedor.comprarProducto(producto, pago);
        return expendedor.getProductoComprado();
    }

    private int vuelto() {
        int vuelto = 0;
        while (true) {
            Moneda mVuelto = expendedor.getMonedaVuelto();
            if (mVuelto == null) break;
            assertEquals(100, mVuelto.getValor());
            vuelto += mVuelto.getValor();
        }
        return vuelto;
    }
}
