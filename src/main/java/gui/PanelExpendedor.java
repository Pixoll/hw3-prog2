package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;

/**
 * Panel Máquina expendedora que se encuentra a la derecha de la GUI.
 */
public class PanelExpendedor extends JPanel {
    /**
     * Es toda la GUI.
     */
    private final PanelPrincipal panelPrincipal;
    /**
     * Es el botón que activa el popup del Numpad del expendedor.
     */
    private final PanelExpendedorNumpad panelNumpad;
    /**
     * Es el panel donde se ve el producto seleccionado.
     */
    private final PanelExpendedorProductoPreview panelProductoPreview;
    /**
     * Es el botón para abrir el popup donde se inserta la moneda.
     */
    private final PanelExpendedorInsertarMoneda panelInsertarMoneda;
    /**
     * Es el panel donde se ven los productos disponibles.
     */
    private final PanelVentanaProductos panelVentanaProductos;
    /**
     * Es el panel donde aparece el producto obtenido.
     */
    private final PanelExpendedorObtenerProducto panelObtenerProducto;
    /**
     * Es la imagen de la maquína expendedora.
     */
    private final Image imagenMaquina;
    /**
     * Son los bordes del panel.
     */
    private final Rectangle bordes;
    /**
     * Son los bordes del numpad.
     */
    private final Rectangle numpadBordes;
    /**
     * Son los bordes del panel preview.
     */
    private final Rectangle previewBordes;
    /**
     * Son los bordes de donde se inserta la moneda.
     */
    private final Rectangle insertarMonedaBordes;
    /**
     * Son los bordes de donde se encuentran los productos.
     */
    private final Rectangle ventanaProductosBordes;
    /**
     * Son los bordes de donde se obtienen los productos.
     */
    private final Rectangle obtenerProductoBordes;
    /**
     * Prohíbe que se calculen los bordes más de una vez.
     */
    private boolean bordesCalculados;
    /**
     * Es el expendedor que tiene los productos.
     */
    private final Expendedor expendedorProductos;
    /**
     * Es la imagen del producto comprado.
     */
    private ImagenConLabel imagenProductoComprado;

    /**
     * Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     *
     * @param panelPrincipal Panel principal que tiene toda la GUI.
     */
    public PanelExpendedor(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.imagenMaquina = ImagenRecurso.MAQUINA.getImagen();
        this.bordes = new Rectangle();
        this.numpadBordes = new Rectangle();
        this.previewBordes = new Rectangle();
        this.insertarMonedaBordes = new Rectangle();
        this.ventanaProductosBordes = new Rectangle();
        this.obtenerProductoBordes = new Rectangle();
        this.bordesCalculados = false;
        this.expendedorProductos = new Expendedor(7);
        this.imagenProductoComprado = null;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());

        this.panelNumpad = new PanelExpendedorNumpad(this);
        this.panelProductoPreview = new PanelExpendedorProductoPreview(this);
        this.panelInsertarMoneda = new PanelExpendedorInsertarMoneda(this);
        this.panelVentanaProductos = new PanelVentanaProductos(this);
        this.panelObtenerProducto = new PanelExpendedorObtenerProducto(
                this,
                ImagenRecurso.BOTON_PUSH,
                ImagenRecurso.BOTON_PUSH_BORDE
        );

        this.add(this.panelNumpad);
        this.add(this.panelProductoPreview);
        this.add(this.panelInsertarMoneda);
        this.add(this.panelVentanaProductos);
        this.add(this.panelObtenerProducto);
    }

    /**
     * Da el expendedor que tiene los productos.
     *
     * @return El expendedor que tiene los productos.
     */
    public Expendedor getExpendedorProductos() {
        return this.expendedorProductos;
    }

    /**
     * Da el comprador.
     *
     * @return El comprador.
     */
    public PanelComprador getPanelComprador() {
        return this.panelPrincipal.getPanelComprador();
    }

    /**
     * Da el panel Numpad.
     *
     * @return El panel Numpad.
     */
    public PanelExpendedorNumpad getPanelNumpad() {
        return this.panelNumpad;
    }

    /**
     * Da el panel que muestra la preview del producto.
     *
     * @return El panel que muestra la preview del producto.
     */
    public PanelExpendedorProductoPreview getPanelProductoPreview() {
        return this.panelProductoPreview;
    }

    /**
     * Da el botón para abrir el popup donde se inserta la moneda.
     *
     * @return El botón para abrir el popup donde se inserta la moneda.
     */
    public PanelExpendedorInsertarMoneda getPanelInsertarMoneda() {
        return this.panelInsertarMoneda;
    }

    /**
     * Da el panel donde aparece el producto obtenido.
     *
     * @return El panel donde aparece el producto obtenido.
     */
    public PanelExpendedorObtenerProducto getPanelObtenerProducto() {
        return this.panelObtenerProducto;
    }

    /**
     * Compra un producto.
     *
     * @param tipo   Es el tipo de producto.
     * @param moneda Es la moneda que se usa para pagar.
     */
    public void comprarProducto(TipoProductos tipo, Moneda moneda) {
        try {
            final Comprador comprador = new Comprador(tipo, moneda, this.expendedorProductos);
            final Producto productoComprado = comprador.getProductoComprado();
            System.out.println("Compraste: " + productoComprado.getClass().getName());
            System.out.println("Vuelto: $" + (100 * comprador.getMonedasVuelto().size()));

            this.panelVentanaProductos.actualizarProductos();
            final ImagenConLabel imagenProducto = this.panelVentanaProductos
                    .getImagenProductoComprado(tipo, productoComprado.getSerie());
            this.setImagenProductoComprado(imagenProducto);

            this.panelVentanaProductos.repaint();
        } catch (Exception error) {
            System.out.println(error.toString());
        }
    }

    /**
     * Establece la imagen del producto comprado.
     *
     * @param imagen Imagen del producto comprado.
     */
    public void setImagenProductoComprado(ImagenConLabel imagen) {
        this.imagenProductoComprado = imagen;
        final Rectangle bordesComprador = this.getPanelComprador().getBordes();
        imagen.setLocation(
                bordesComprador.x,
                bordesComprador.height - imagen.getHeight()
        );
    }

    /**
     * Da la imagen del producto comprado.
     *
     * @return La imagen del producto comprado.
     */
    public ImagenConLabel getImagenProductoComprado() {
        return this.imagenProductoComprado;
    }

    /**
     * Calcula los bordes por coordenadas de un panel dentro de la máquina expendedora.
     *
     * @param bordes         El puntero de los bordes a calcular.
     * @param maquinaScaling La escala de la máquina.
     * @param x              Posición X.
     * @param y              Posición Y.
     * @param width          Ancho.
     * @param height         Altura.
     */
    private void calcularBordesPorCoords(Rectangle bordes, float maquinaScaling, int x, int y, int width, int height) {
        bordes.x = this.bordes.x + (int) (x * maquinaScaling);
        bordes.y = this.bordes.y + (int) (y * maquinaScaling);
        bordes.width = (int) (width * maquinaScaling);
        bordes.height = (int) (height * maquinaScaling);
    }

    /**
     * Calcula todos los bordes.
     */
    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final int panelPrincipalWidth = this.panelPrincipal.getWidth();
        final int panelPrincipalHeight = this.panelPrincipal.getHeight();
        final int maquinaWidth = this.imagenMaquina.getWidth(null);
        final int maquinaHeight = this.imagenMaquina.getHeight(null);

        final float maquinaScaling = (float) (panelPrincipalHeight * 0.95) / maquinaHeight;
        this.bordes.width = (int) (maquinaWidth * maquinaScaling);
        this.bordes.height = (int) (maquinaHeight * maquinaScaling);
        this.bordes.y = (panelPrincipalHeight - this.bordes.height) / 2;
        this.bordes.x = panelPrincipalWidth - this.bordes.width - (this.bordes.y * 2);

        this.calcularBordesPorCoords(this.numpadBordes, maquinaScaling, 203, 204, 56, 96);
        this.calcularBordesPorCoords(this.previewBordes, maquinaScaling, 207, 97, 48, 48);
        this.calcularBordesPorCoords(this.insertarMonedaBordes, maquinaScaling, 205, 156, 52, 40);
        this.calcularBordesPorCoords(this.ventanaProductosBordes, maquinaScaling, 37, 23, 154, 323);
        this.calcularBordesPorCoords(this.obtenerProductoBordes, maquinaScaling, 28, 364, 152, 72);

        this.bordesCalculados = true;
    }

    /**
     * Da los bordes del panel.
     *
     * @return Los bordes del panel.
     */
    public Rectangle getBordes() {
        return this.bordes;
    }

    /**
     * Da los bordes del numpad.
     *
     * @return Los bordes del numpad.
     */
    public Rectangle getNumpadBordes() {
        return this.numpadBordes;
    }

    /**
     * Da los bordes del panel preview.
     *
     * @return Los bordes del panel preview.
     */
    public Rectangle getPreviewBordes() {
        return this.previewBordes;
    }

    /**
     * Da los bordes de donde se inserta la moneda.
     *
     * @return Los bordes de donde se inserta la moneda.
     */
    public Rectangle getInsertarMonedaBordes() {
        return this.insertarMonedaBordes;
    }

    /**
     * Da los bordes de donde se encuentran los productos.
     *
     * @return Los bordes de donde se encuentran los productos.
     */
    public Rectangle getVentanaProductosBordes() {
        return this.ventanaProductosBordes;
    }

    /**
     * Da los bordes de donde se obtienen los productos.
     *
     * @return Los bordes de donde se obtienen los productos.
     */
    public Rectangle getObtenerProductoBordes() {
        return this.obtenerProductoBordes;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelPrincipal.getBounds());
        this.calcularBordes();
        super.paint(graphics);

        Util.drawImage(graphics, this.imagenMaquina, this.bordes);
        this.panelNumpad.repaint();
        this.panelProductoPreview.repaint();
        this.panelInsertarMoneda.repaint();
        this.panelVentanaProductos.repaint();
        this.panelObtenerProducto.repaint();
    }
}
