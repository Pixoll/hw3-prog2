/**
 * El comprador la usa en el expendedor para obtener un producto.
 */
public abstract class Moneda implements Comparable<Moneda> {
    /**
     * El comprador la usa en el expendedor para obtener un producto.
     */
    public Moneda() {
    }

    /**
     * Da el puntero de la moneda para saber si dos de estas son diferentes.
     * @return El puntero.
     */
    public Moneda getSerie() {
        return this;
    }

    /**
     * Obtiene el valor de la moneda.
     * @return El valor de la moneda.
     */
    public abstract int getValor();

    @Override
    public int compareTo(Moneda moneda) {
        return Integer.compare(this.getValor(), moneda.getValor());
    }

    public String toString() {
        return "$" + this.getValor();
    }
}
