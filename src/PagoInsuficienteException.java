/**
 * Excepción tirada cuando el valor pagado es menor al del producto.
 */
public class PagoInsuficienteException extends Exception {
    /**
     * Excepción tirada cuando el valor pagado es menor al del producto.
     * @param mensaje Mensaje detallando el error.
     */
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
