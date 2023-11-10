package backend;

import java.util.ArrayList;

/**
 * Depósito de items.
 * @param <T> Es el tipo del item.
 */
public class Deposito<T> {
    /**
     * Si se pueden remover items o no.
     */
    private final boolean puedeRemover;
    /**
     * Almacena los items.
     */
    private final ArrayList<T> items;

    /**
     * Depósito de items.
     */
    public Deposito() {
        this.puedeRemover = true;
        this.items = new ArrayList<>();
    }

    public Deposito(boolean puedeRemover) {
        this.puedeRemover = puedeRemover;
        this.items = new ArrayList<>();
    }

    /**
     * Almacena un item.
     * @param item Es el item.
     */
    public void add(T item) {
        this.items.add(item);
    }

    /**
     * Lee el item en el index sin removerlo del depósito.
     * @param index El index a leer.
     * @return El item en el index correspondiente.
     */
    public T leer(int index) {
        return this.items.get(index);
    }

    /**
     * Remueve el primer item almacenado. Si puedeRemover falso, no elimina nada y devuelve null.
     * @return El item. null si está vacío o si puedeRemover es falso.
     */
    public T get() {
        if (!this.puedeRemover || this.items.isEmpty()) return null;
        return this.items.remove(0);
    }

    /**
     * Devuelve el número de elementos dentro del depósito.
     * @return El número de elementos dentro del depósito.
     */
    public int size() {
        return this.items.size();
    }
}
