/**
 * Tipo de producto que se encuentra en el expendedor.
 */
public abstract class Bebida extends Producto {
    /**
     * Tipo de producto que se encuentra en el expendedor.
     * @param serie Número identificador del producto.
     */
    public Bebida(int serie) {
       super(serie);
    }

    /**
     * Acción del cliente después de recibir su bebida desde el expendedor.
     * @return El sabor de la bebida.
     */
    public abstract String beber();
}
