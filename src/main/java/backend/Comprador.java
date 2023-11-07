package backend;

/**
 * Quien compra un producto en el expendedor.
 */
public class Comprador {
    /**
     * Lo que sabe el producto.
     */
    private String sabor;
    /**
     * Lo que se devuelve si se paga un valor mayor a lo comprado.
     */
    private int vuelto;

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
        Producto producto = expendedor.getProductoComprado();

        if (producto instanceof Bebida) {
            this.sabor = ((Bebida)producto).beber();
        } else if (producto instanceof Dulce) {
            this.sabor = ((Dulce)producto).comer();
        }

        while (true) {
            Moneda vuelto = expendedor.getMonedaVuelto();
            if (vuelto == null) break;
            this.vuelto += vuelto.getValor();
        }
    }

    /**
     * Cuanto recibe el comprador de vuelto.
     * @return El vuelto.
     */
    public int cuantoVuelto() {
        return this.vuelto;
    }

    /**
     * El sabor de lo que compró.
     * @return El sabor del producto.
     */
    public String queCompraste() {
        return this.sabor;
    }
}
