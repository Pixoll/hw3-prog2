package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel del Comprador que se encuentra a la izquierda del GUI.
 */
public class PanelComprador extends JPanel {
    /**
     * Es toda la GUI.
     */
    private final PanelPrincipal panelPrincipal;
    /**
     * Es el Numpad para seleccionar el producto.
     */
    private final PanelPopupNumpad panelPopupNumpad;
    /**
     * Es el panel donde se selecciona la moneda a usar.
     */
    private final PanelPopupInsertarMoneda panelPopupInsertarMoneda;
    /**
     * Una colección con todos los popup.
     */
    private final ArrayList<PanelPopup> panelesPopup;
    /**
     * Son los bordes del panel.
     */
    private final Rectangle bordes;
    /**
     * Es imagen del fondo GUI.
     */
    private final Image imagenFondo;
    /**
     * Prohíbe que se calculen los bordes más de una vez.
     */
    private boolean bordesCalculados;
    /**
     * Si debe cambiar el producto comprado o no
     */
    private ImagenConLabel imagenProductoComprado;

    /**
     * Panel del Comprador que se encuentra a la izquierda del GUI.
     *
     * @param panelPrincipal Panel principal que tiene toda la GUI.
     */
    public PanelComprador(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.bordes = new Rectangle();
        this.bordesCalculados = false;
        this.imagenFondo = ImagenRecurso.FONDO.getImagen();
        this.imagenProductoComprado = null;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());

        this.panelesPopup = new ArrayList<>();
        this.panelPopupNumpad = new PanelPopupNumpad(this);
        this.panelPopupInsertarMoneda = new PanelPopupInsertarMoneda(this);

        this.add(this.panelPopupNumpad);
        this.add(this.panelPopupInsertarMoneda);
        this.panelesPopup.add(this.panelPopupNumpad);
        this.panelesPopup.add(this.panelPopupInsertarMoneda);
    }

    /**
     * Da el panel expendedor.
     *
     * @return El panel expendedor.
     */
    public PanelExpendedor getPanelExpendedor() {
        return this.panelPrincipal.getPanelExpendedor();
    }

    /**
     * Da el Numpad para seleccionar el producto.
     *
     * @return El Numpad para seleccionar el producto.
     */
    public PanelPopupNumpad getPanelPopupNumpad() {
        return this.panelPopupNumpad;
    }

    /**
     * Da el panel donde se selecciona la moneda a usar.
     *
     * @return El panel donde se selecciona la moneda a usar.
     */
    public PanelPopupInsertarMoneda getPanelPopupInsertarMoneda() {
        return this.panelPopupInsertarMoneda;
    }

    /**
     * Da una colección con todos los popup.
     *
     * @return La colección con todos los popup.
     */
    public ArrayList<PanelPopup> getPanelesPopup() {
        return this.panelesPopup;
    }

    /**
     * Calcula los bordes.
     */
    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final Rectangle expendedorBordes = this.panelPrincipal.getPanelExpendedor().getBordes();

        this.bordes.x = expendedorBordes.y * 2;
        this.bordes.y = expendedorBordes.y;
        this.bordes.width = expendedorBordes.x - (this.bordes.x * 2);
        this.bordes.height = expendedorBordes.height;

        this.setBounds(0, 0, this.bordes.width + (this.bordes.x * 2), this.panelPrincipal.getHeight());

        this.bordesCalculados = true;
    }

    /**
     * Da los bordes del panel.
     * @return Los bordes del panel.
     */
    public Rectangle getBordes() {
        return this.bordes;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        final Rectangle bordesFondo = new Rectangle(this.panelPrincipal.getBordesFondo().getSize());
        int fondoX = 0;
        while (fondoX < this.getWidth()) {
            bordesFondo.x = fondoX;
            Util.drawImage(graphics, this.imagenFondo, bordesFondo);
            fondoX += bordesFondo.width;
        }

        final ImagenConLabel imagenProductoComprado = this.getPanelExpendedor().getImagenProductoComprado();
        if (imagenProductoComprado != null) {
            if (this.imagenProductoComprado != null) this.remove(this.imagenProductoComprado);
            this.add(imagenProductoComprado);
            imagenProductoComprado.repaint();
        }
    }
}
