package backend;

/**
 * El comprador la usa en el expendedor para obtener un producto.
 */
public abstract class Moneda implements Comparable<Moneda> {
    private static int CANTIDAD = 0;
    private final int valor;
    private final int serie;

    /**
     * El comprador la usa en el expendedor para obtener un producto.
     */
    public Moneda(int valor) {
        this.valor = valor;
        this.serie = CANTIDAD;
        CANTIDAD++;
    }

    /**
     * Serie única para cada moneda creada.
     * @return Serie de la moneda.
     */
    public int getSerie() {
        return this.serie;
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
