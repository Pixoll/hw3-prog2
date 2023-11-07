package backend;

/**
 * Un producto vendido por el expendedor.
 */
public abstract class Producto {
    /**
     * Número identificador del producto.
     */
    private final int serie;

    /**
     * Un producto vendido por el expendedor.
     * @param serie Número identificador del producto.
     */
    public Producto(int serie) {
        this.serie = serie;
    }

    /**
     * Obtiene la serie del producto.
     * @return La serie del producto.
     */
    public int getSerie() {
        return this.serie;
    }
}
