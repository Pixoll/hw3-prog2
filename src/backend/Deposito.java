package backend;

import java.util.ArrayList;

/**
 * Depósito de items.
 * @param <T> Es el tipo del item.
 */
public class Deposito<T> {
    /**
     * Almacena los items.
     */
    private final ArrayList<T> almacen;

    /**
     * Depósito de items.
     */
    public Deposito() {
        this.almacen = new ArrayList<>();
    }

    /**
     * Almacena un item.
     * @param item Es el item.
     */
    public void add(T item) {
        this.almacen.add(item);
    }

    /**
     * Remueve el primer item almacenado.
     * @return El item.
     */
    public T get() {
        if (this.almacen.isEmpty()) return null;
        return this.almacen.remove(0);
    }
}
