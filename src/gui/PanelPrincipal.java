package gui;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private final PanelComprador panelComprador;
    private final PanelExpendedor panelExpendedor;
    private final Image imagenFondo;
    private final Rectangle bordesFondo;
    private boolean bordesCalculados;

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
        popupNumpad.setPanelExpendedorNumpad(expendedorNumpad);

        expendedorInsertarMoneda.setPanelPopup(popupInsertarMoneda);
        popupInsertarMoneda.setPanelInsertarMoneda(expendedorInsertarMoneda);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);

        this.imagenFondo = ImagenRecurso.FONDO.getImagen();

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        final int fondoWidth = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        final int fondoHeight = (int) (this.imagenFondo.getHeight(null) * fondoScaling);

        this.bordesFondo = new Rectangle(fondoWidth, fondoHeight);
        this.bordesCalculados = false;
    }

    public PanelComprador getPanelComprador() {
        return this.panelComprador;
    }

    public PanelExpendedor getPanelExpendedor() {
        return this.panelExpendedor;
    }

    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        this.bordesFondo.width = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        this.bordesFondo.height = this.getHeight();

        this.bordesCalculados = true;
    }

    public Rectangle getBordesFondo() {
        return this.bordesFondo;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        int fondoX = 0;
        while (fondoX < this.getWidth()) {
            graphics.drawImage(this.imagenFondo, fondoX, 0, this.bordesFondo.width, this.bordesFondo.height, null);
            fondoX += this.bordesFondo.width;
        }

        this.panelExpendedor.repaint();
        this.panelComprador.repaint();
    }
}
