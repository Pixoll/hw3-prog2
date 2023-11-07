package backend;

/**
 * Un tipo de bebida.
 */
public class CocaCola extends Bebida {
    /**
     * Un tipo de bebida.
     * @param serie Número identificador del producto.
     */
    public CocaCola(int serie) {
        super(serie);
    }

    public String beber() {
        return "cocacola";
    }
}
