package backend;

/**
 * Un tipo de bebida.
 */
public class Fanta extends Bebida {
    /**
     * Un tipo de bebida.
     * @param serie Número identificador del producto.
     */
    public Fanta(int serie) {
        super(serie);
    }

    public String beber() {
        return "fanta";
    }
}
