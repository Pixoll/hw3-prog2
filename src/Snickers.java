/**
 * Un tipo de dulce.
 * "You're not you when you're hungry"
 */
public class Snickers extends Dulce {
    /**
     * Un tipo de dulce.
     * "You're not you when you're hungry"
     * @param serie Número identificador del producto.
     */
    public Snickers(int serie) {
        super(serie);
    }

    public String comer() {
        return "here have a snickers, you're not you when you're hungry";
    }
}
