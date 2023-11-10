package backend;

import java.util.ArrayList;

/**
 * Máquina expendedora que vende los productos.
 */
public class Expendedor {
    /**
     * Array con los depósitos de todos los tipos posibles de productos
     */
    private final ArrayList<Deposito<Producto>> productos;
    /**
     * Depósito con todos las monedas del vuelto.
     */
    private final Deposito<Moneda> monedasVuelto;
    /**
     * Almacena el producto comprado.
     */
    private Producto productoComprado;
    /**
     * Depósito con todas las monedas usadas al pagar.
     */
    public final Deposito<Moneda> monedasPagadas;

    /**
     * Máquina expendedora que vende los productos.
     * @param numeroProductos Número de productos que vende la máquina.
     */
    public Expendedor(int numeroProductos) {
        this.productos = new ArrayList<>();
        this.monedasVuelto = new Deposito<>();
        this.monedasPagadas = new Deposito<>(false);

        for (TipoProductos tipo : TipoProductos.values()) {
            final Deposito<Producto> deposito = new Deposito<>();
            for (int i = 0; i < numeroProductos; i++) {
                deposito.add(tipo.crearProducto(i));
            }
            this.productos.add(deposito);
        }
    }

    /**
     * Compra un producto de la máquina expendedora.
     * @param moneda Pago del producto.
     * @param tipo Tipo del producto a comprar.
     * @throws NoHayProductoException Tirada cuando no hay productos en el depósito.
     * @throws PagoIncorrectoException Tirada cuando el pago es incorrecto (null).
     * @throws PagoInsuficienteException Tirada cuando el valor pagado es menor al del producto.
     */
    public void comprarProducto(TipoProductos tipo, Moneda moneda)
            throws NoHayProductoException, PagoIncorrectoException, PagoInsuficienteException {
        if (moneda == null) {
            throw new PagoIncorrectoException("No ingresaste dinero.");
        }

        if (tipo == null) {
            throw new NoHayProductoException("No existe el producto null");
        }

        if (moneda.getValor() < tipo.getPrecio()) {
            this.monedasVuelto.add(moneda);
            throw new PagoInsuficienteException("Debes ingresar al menos $"
                    + tipo.getPrecio()
                    + " (ingresaste $" + moneda.getValor() + ")");
        }

        Deposito<Producto> deposito = this.productos.get(tipo.ordinal());
        Producto producto = deposito.get();
        if (producto == null) {
            this.monedasVuelto.add(moneda);
            throw new NoHayProductoException("No quedan más " + tipo.getTipo());
        }

        int vuelto = moneda.getValor() - tipo.getPrecio();
        while (vuelto != 0) {
            this.monedasVuelto.add(new Moneda100());
            vuelto -= 100;
        }

        this.productoComprado = producto;
        this.monedasPagadas.add(moneda);
    }

    /**
     * Devuelve el producto comprado.
     * @return El producto comprado, si es que no hubo errores al comprarlo.
     */
    public Producto getProductoComprado() {
        return this.productoComprado;
    }

    /**
     * Devuelve el depósito de productos correspondiente al tipo.
     * @param tipo El tipo de producto.
     * @return El depósito de productos correspondiente.
     */
    public Deposito<Producto> getDepositoProducto(TipoProductos tipo) {
        return this.productos.get(tipo.ordinal());
    }

    /**
     * Obtiene una moneda del vuelto
     * @return Una moneda del vuelto.
     */
    public Moneda getMonedaVuelto() {
        return this.monedasVuelto.get();
    }
}
