/**
 * Excepción tirada cuando no hay productos en el depósito.
 */
public class NoHayProductoException extends Exception {
    /**
     * Excepción tirada cuando no hay productos en el depósito.
     * @param mensaje Mensaje detallando el error.
     */
    public NoHayProductoException(String mensaje) {
        super(mensaje);
    }
}
