/**
 * Tipo de producto que se encuentra en el expendedor.
 */
public abstract class Dulce extends Producto {
    /**
     * Tipo de producto que se encuentra en el expendedor.
     * @param serie Número identificador del producto.
     */
    public Dulce(int serie) {
        super(serie);
    }

    /**
     * Acción del cliente después de recibir su dulce desde el expendedor.
     * @return El sabor del dulce.
     */
    public abstract String comer();
}
