/**
 * Excepción tirada cuando el pago es incorrecto.
 */
public class PagoIncorrectoException extends Exception {
    /**
     * Excepción tirada cuando el pago es incorrecto.
     * @param mensaje Mensaje detallando el error.
     */
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}
