package backend;

/**
 * Máquina expendedora que vende los productos.
 */
public class Expendedor {
    /**
     * Depósito con todas las Coca Cola.
     */
    private final Deposito<Producto> cocaCola;
    /**
     * Depósito con todas las backend.Sprite.
     */
    private final Deposito<Producto> sprite;
    /**
     * Depósito con todas las backend.Fanta.
     */
    private final Deposito<Producto> fanta;
    /**
     * Depósito con todas los backend.Snickers.
     */
    private final Deposito<Producto> snickers;
    /**
     * Depósito con todos los Super 8.
     */
    private final Deposito<Producto> super8;
    /**
     * Depósito con todos las monedas del vuelto.
     */
    private final Deposito<Moneda> monedasVuelto;

    /**
     * Máquina expendedora que vende los productos.
     * @param numeroProductos Número de productos que vende la máquina.
     */
    public Expendedor(int numeroProductos) {
        this.cocaCola = new Deposito<>();
        this.fanta = new Deposito<>();
        this.sprite = new Deposito<>();
        this.snickers = new Deposito<>();
        this.super8 = new Deposito<>();
        this.monedasVuelto = new Deposito<>();

        for (int i = 0; i < numeroProductos; i++) {
            this.cocaCola.add(new CocaCola(i));
            this.fanta.add(new Fanta(i));
            this.sprite.add(new Sprite(i));
            this.snickers.add(new Snickers(i));
            this.super8.add(new Super8(i));
        }
    }

    /**
     * Compra un producto de la máquina expendedora.
     * @param moneda Pago del producto.
     * @param tipo Tipo del producto a comprar.
     * @return El producto comprado, si no hubo errores.
     * @throws NoHayProductoException Tirada cuando no hay productos en el depósito.
     * @throws PagoIncorrectoException Tirada cuando el pago es incorrecto (null).
     * @throws PagoInsuficienteException Tirada cuando el valor pagado es menor al del producto.
     */
    public Producto comprarProducto(TipoProductos tipo, Moneda moneda)
            throws NoHayProductoException, PagoIncorrectoException, PagoInsuficienteException {
        if (moneda == null) {
            throw new PagoIncorrectoException("No ingresaste dinero.");
        }
        if (moneda.getValor() < tipo.getPrecio()) {
            this.monedasVuelto.add(moneda);
            throw new PagoInsuficienteException("Debes ingresar al menos $"
                    + tipo.getPrecio()
                    + " (ingresaste $" + moneda.getValor() + ")");
        }

        Deposito<Producto> deposito = tipo == TipoProductos.COCA_COLA ? this.cocaCola
                : tipo == TipoProductos.FANTA ? this.fanta
                : tipo == TipoProductos.SPRITE ? this.sprite
                : tipo == TipoProductos.SNICKERS ? this.snickers
                : tipo == TipoProductos.SUPER8 ? this.super8
                // Nunca pasa, es imposible entregar algo que no sea backend.TipoProductos
                : null;
        if (deposito == null) {
            this.monedasVuelto.add(moneda);
            throw new NoHayProductoException("El tipo de producto \"" + tipo + "\" no existe");
        }

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

        return producto;
    }

    /**
     * Obtiene una moneda del vuelto
     * @return Una moneda del vuelto.
     */
    public Moneda getMonedaVuelto() {
        return this.monedasVuelto.get();
    }
}
