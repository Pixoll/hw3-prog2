package backend;

/**
 * Constantes asociadas todos los tipos de productos vendidos.
 */
public enum TipoProductos {
    /**
     * Representa la bebida Coca Cola.
     */
    COCA_COLA("Coca Cola", 1000) {
        @Override
        public CocaCola crearProducto(int serie) {
            return new CocaCola(serie);
        }
    },
    /**
     * Representa la bebida backend.Fanta.
     */
    FANTA("Fanta", 800) {
        @Override
        public Fanta crearProducto(int serie) {
            return new Fanta(serie);
        }
    },
    /**
     * Representa la bebida backend.Sprite.
     */
    SPRITE("Sprite", 900) {
        @Override
        public Sprite crearProducto(int serie) {
            return new Sprite(serie);
        }
    },
    /**
     * Representa el dulce backend.Snickers.
     * "You're not you when you're hungry"
     */
    SNICKERS("Snickers", 1200) {
        @Override
        public Snickers crearProducto(int serie) {
            return new Snickers(serie);
        }
    },
    /**
     * Representa el dulce Super 8.
     */
    SUPER8("Super 8", 100) {
        @Override
        public Super8 crearProducto(int serie) {
            return new Super8(serie);
        }
    };

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

    /**
     * Crea un producto de este tipo.
     * @param serie La serie del producto.
     * @return El producto de este tipo.
     */
    public abstract Producto crearProducto(int serie);
}
