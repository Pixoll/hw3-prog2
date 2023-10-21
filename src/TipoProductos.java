/**
 * Constantes asociadas todos los tipos de productos vendidos.
 */
public enum TipoProductos {
    /**
     * Representa la bebida Coca Cola.
     */
    COCA_COLA("Coca Cola", 1000),
    /**
     * Representa la bebida Fanta.
     */
    FANTA("Fanta", 800),
    /**
     * Representa la bebida Sprite.
     */
    SPRITE("Sprite", 900),
    /**
     * Representa el dulce Snickers.
     * "You're not you when you're hungry"
     */
    SNICKERS("Snickers", 1200),
    /**
     * Representa el dulce Super 8.
     */
    SUPER8("Super 8", 100);

    /**
     * Texto que representa el tipo del producto.
     */
    private final String tipo;
    /**
     * El precio del producto.
     */
    private final int precio;

    /**
     * Crea un tipo de producto
     * @param tipo El tipo del producto
     * @param precio El precio del producto
     */
    TipoProductos(String tipo, int precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * Obtiene el tipo del producto.
     * @return El tipo del producto.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public int getPrecio() {
        return this.precio;
    }

    public static TipoProductos valueOf(int cardinal) {
        if (cardinal < 0 || cardinal > TipoProductos.values().length - 1) return null;
        return TipoProductos.values()[cardinal];
    }
}
