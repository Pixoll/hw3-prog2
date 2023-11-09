package backend;

import java.util.ArrayList;

/**
 * Quien compra un producto en el expendedor.
 */
public class Comprador {
    /**
     * El producto comprado.
     */
    private final Producto productoComprado;
    /**
     * Monedas obtenidas del vuelto.
     */
    private final ArrayList<Moneda> monedasVuelto;

    /**
     * Quien compra un producto en el expendedor.
     * @param tipoProducto El tipo de producto que se compra.
     * @param moneda Con la que se compra el producto.
     * @param expendedor Máquina expendedora que vende los productos.
     * @throws NoHayProductoException Excepción tirada cuando no hay productos en el depósito.
     * @throws PagoIncorrectoException Excepción tirada cuando el pago es incorrecto.
     * @throws PagoInsuficienteException Excepción tirada cuando el valor pagado es menor al del producto.
     */
    public Comprador(TipoProductos tipoProducto, Moneda moneda, Expendedor expendedor)
            throws NoHayProductoException, PagoIncorrectoException, PagoInsuficienteException {
        expendedor.comprarProducto(tipoProducto, moneda);
        this.productoComprado = expendedor.getProductoComprado();
        this.monedasVuelto = new ArrayList<>();

        while (true) {
            Moneda vuelto = expendedor.getMonedaVuelto();
            if (vuelto == null) break;
            this.monedasVuelto.add(vuelto);
        }
    }

    /**
     * El sabor de lo que compró.
     * @return El sabor del producto.
     */
    public Producto getProductoComprado() {
        return this.productoComprado;
    }

    /**
     * Devuelve las monedas del vuelto.
     * @return Las monedas del vuelto.
     */
    public ArrayList<Moneda> getMonedasVuelto() {
        return this.monedasVuelto;
    }
}
