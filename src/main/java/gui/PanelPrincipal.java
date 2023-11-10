package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Es toda la GUI.
 */
public class PanelPrincipal extends JPanel {
    /**
     * Panel del Comprador que se encuentra a la izquierda del GUI.
     */
    private final PanelComprador panelComprador;

    /**
     * Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     */
    private final PanelExpendedor panelExpendedor;
    /**
     * Es imagen del fondo GUI.
     */
    private final Image imagenFondo;
    /**
     * Son los bordes del fondo.
     */
    private final Rectangle bordesFondo;
    /**
     * Prohíbe que se calculen los bordes más de una vez.
     */
    private boolean bordesCalculados;

    /**
     * Es toda la GUI.
     * @param width ancho del panel principal.
     * @param height alto del panel principal.
     */
    public PanelPrincipal(int width, int height) {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);

        this.panelComprador = new PanelComprador(this);
        this.panelExpendedor = new PanelExpendedor(this);

        final PanelExpendedorNumpad expendedorNumpad = this.panelExpendedor.getPanelNumpad();
        final PanelExpendedorInsertarMoneda expendedorInsertarMoneda = this.panelExpendedor.getPanelInsertarMoneda();
        final PanelPopupNumpad popupNumpad = this.panelComprador.getPanelPopupNumpad();
        final PanelPopupInsertarMoneda popupInsertarMoneda = this.panelComprador.getPanelPopupInsertarMoneda();

        expendedorNumpad.setPanelPopup(popupNumpad);
        popupNumpad.setPanelProductoPreview(this.panelExpendedor.getPanelProductoPreview());
        popupNumpad.setPanelExpendedorSeleccionable(expendedorNumpad);

        expendedorInsertarMoneda.setPanelPopup(popupInsertarMoneda);
        popupInsertarMoneda.setPanelExpendedorSeleccionable(expendedorInsertarMoneda);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);

        this.imagenFondo = ImagenRecurso.FONDO.getImagen();

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        final int fondoWidth = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        final int fondoHeight = (int) (this.imagenFondo.getHeight(null) * fondoScaling);

        this.bordesFondo = new Rectangle(fondoWidth, fondoHeight);
        this.bordesCalculados = false;
    }

    /**
     * Da el panel del comprador.
     * @return El panel del comprador.
     */
    public PanelComprador getPanelComprador() {
        return this.panelComprador;
    }

    /**
     * Da el panel del expendedor.
     * @return El panel del expendedor.
     */
    public PanelExpendedor getPanelExpendedor() {
        return this.panelExpendedor;
    }

    /**
     * Calcula los bordes.
     */
    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        this.bordesFondo.width = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        this.bordesFondo.height = this.getHeight();

        this.bordesCalculados = true;
    }

    /**
     * Da los bordes del fondo.
     * @return Los bordes del fondo.
     */
    public Rectangle getBordesFondo() {
        return this.bordesFondo;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        int fondoX = 0;
        while (fondoX < this.getWidth()) {
            final Rectangle bordesImagen = new Rectangle(fondoX, 0, this.bordesFondo.width, this.bordesFondo.height);
            Util.drawImage(graphics, this.imagenFondo, bordesImagen);
            fondoX += this.bordesFondo.width;
        }

        this.panelExpendedor.repaint();
        this.panelComprador.repaint();
    }
}
