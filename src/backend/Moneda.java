package backend;

/**
 * El comprador la usa en el expendedor para obtener un producto.
 */
public abstract class Moneda implements Comparable<Moneda> {
    private final int valor;

    /**
     * El comprador la usa en el expendedor para obtener un producto.
     */
    public Moneda(int valor) {
        this.valor = valor;
    }

    /**
     * Serie única para cada moneda creada.
     * @return Serie de la moneda.
     */
    public String getSerie() {
        return Integer.toString(this.hashCode(), 16);
    }

    /**
     * Obtiene el valor de la moneda.
     * @return El valor de la moneda.
     */
    public int getValor() {
        return this.valor;
    }

    @Override
    public int compareTo(Moneda moneda) {
        return Integer.compare(this.getValor(), moneda.getValor());
    }

    public String toString() {
        return "$" + this.getValor();
    }
}
