import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * BufferedReader que lee el input del comprador en la consola
     */
    private static final BufferedReader leerInput = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Máquina expendedora que contiene todos los productos
     */
    private static final Expendedor expendedor = new Expendedor(5);

    /**
     * Método principal que ejecuta el código
     * @param args Argumentos (no se usan)
     */
    public static void main(String[] args) {
        System.out.println("¡Bienvenido al expendedor mágico!");
        System.out.println("Escribe el tipo de producto que quieres y el monto que deseas pagar.");
        System.out.println("O escribe \"q\" para salir de la tienda.");

        System.out.println();
        System.out.println("Productos válidos:");
        for (int i = 0; i < TipoProductos.values().length; i++) {
            final TipoProductos tipo = TipoProductos.values()[i];
            System.out.println((i + 1) + ": " + tipo.getTipo() + " ($" + tipo.getPrecio() + ")");
        }

        System.out.println();
        System.out.println("Montos válidos: 100, 500, 1000, 1500.");
        System.out.println("Si ingresas menos de 100, se considera que no ingresaste dinero.");
        System.out.println("Para los demás, se considera la moneda por debajo del monto ingresado.");
        System.out.println("Por ejemplo: Monto de 400 crea una moneda de 100.");

        while (true) {
            System.out.println();

            final String inputTipo = Main.leerLinea("Ingresa el tipo de producto: ");
            if (inputTipo.isEmpty()) continue;
            if (inputTipo.equals("q")) break;

            final int indexTipo = Integer.parseInt(inputTipo, 10) - 1;
            final TipoProductos tipo = TipoProductos.valueOf(indexTipo);
            if (tipo == null) {
                System.out.println("Tipo de producto inválido.");
                continue;
            }

            final String inputMonto = Main.leerLinea("Ingresa el monto a pagar: $");
            if (inputMonto.isEmpty()) continue;
            if (inputMonto.equals("q")) break;

            int monto;
            try {
                monto = Integer.parseInt(inputMonto);
            } catch (Exception error) {
                System.out.println("Monto inválido.");
                continue;
            }

            final Moneda moneda = monto < 100 ? null
                    : monto < 500 ? new Moneda100()
                    : monto < 1000 ? new Moneda500()
                    : monto < 1500 ? new Moneda1000()
                    : new Moneda1500();

            System.out.println();
            final Moneda devuelta = Main.comprarProducto(tipo, moneda);
            if (devuelta != null) {
                System.out.println("Se te ha devuelto la moneda de " + devuelta);
            }
        }

        System.out.println("Saliendo de la tienda ¡Esperamos vuelvas pronto!");
    }

    /**
     * Lee una línea de input del comprador
     * @param mensaje Mensaje que se envía al comprador
     * @return El input del comprador
     */
    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        String input = "";
        try {
            while (input.isEmpty()) {
                input = leerInput.readLine();
            }
        } catch (IOException error) {
            System.out.println("Error al leer tu input.");
            error.printStackTrace();
        }
        return input;
    }

    /**
     * Compra un producto
     * @param tipo El tipo del producto
     * @param moneda La moneda de pago
     * @return La moneda entregada si no se pudo hacer la compra. En otro caso es null
     */
    private static Moneda comprarProducto(final TipoProductos tipo, final Moneda moneda) {
        try {
            System.out.println("Comprando " + tipo.getTipo() + " ($" + tipo.getPrecio() + ")" + " con " + moneda);
            final Comprador comprador = new Comprador(tipo, moneda, Main.expendedor);

            System.out.println("Compró: " + comprador.queCompraste());
            System.out.println("Vuelto: $" + comprador.cuantoVuelto());
        } catch (PagoIncorrectoException error) {
            System.out.println("PagoIncorrecto: " + error.getMessage());
            return Main.expendedor.getVuelto();
        } catch (PagoInsuficienteException error) {
            System.out.println("PagoInsuficiente: " + error.getMessage());
            return Main.expendedor.getVuelto();
        } catch (NoHayProductoException error) {
            System.out.println("NoHayProducto: " + error.getMessage());
            return Main.expendedor.getVuelto();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return null;
    }
}
