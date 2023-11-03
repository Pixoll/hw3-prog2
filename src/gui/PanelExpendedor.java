package gui;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private final PanelPrincipal panelPrincipal;
    private final PanelExpendedorNumpad panelNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final Image imagenMaquina;
    private final Rectangle bordes;
    private final Rectangle numpadBordes;
    private final Rectangle previewBordes;
    private boolean bordesCalculados;

    public PanelExpendedor(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.imagenMaquina = ImagenRecurso.MAQUINA.getImagen();
        this.bordes = new Rectangle();
        this.numpadBordes = new Rectangle();
        this.previewBordes = new Rectangle();
        this.bordesCalculados = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());

        this.panelNumpad = new PanelExpendedorNumpad(this);
        this.panelProductoPreview = new PanelExpendedorProductoPreview(this);

        this.add(panelNumpad);
        this.add(this.panelProductoPreview);
    }

    public PanelPrincipal getPanelPrincipal() {
        return this.panelPrincipal;
    }

    public PanelExpendedorNumpad getPanelNumpad() {
        return this.panelNumpad;
    }

    public PanelExpendedorProductoPreview getPanelProductoPreview() {
        return this.panelProductoPreview;
    }

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

        this.numpadBordes.x = this.bordes.x + (int) (205 * maquinaScaling);
        this.numpadBordes.y = this.bordes.y + (int) (206 * maquinaScaling);
        this.numpadBordes.width = (int) (52 * maquinaScaling);
        this.numpadBordes.height = (int) (92 * maquinaScaling);

        this.previewBordes.x = this.bordes.x + (int) (207 * maquinaScaling);
        this.previewBordes.y = this.bordes.y + (int) (97 * maquinaScaling);
        this.previewBordes.width = (int) (48 * maquinaScaling);
        this.previewBordes.height = (int) (48 * maquinaScaling);

        this.bordesCalculados = true;
    }

    public Rectangle getBordes() {
        return this.bordes;
    }

    public Rectangle getNumpadBordes() {
        return this.numpadBordes;
    }

    public Rectangle getPreviewBordes() {
        return this.previewBordes;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelPrincipal.getBounds());
        this.calcularBordes();
        super.paint(graphics);

        graphics.drawImage(this.imagenMaquina, this.bordes.x, this.bordes.y, this.bordes.width, this.bordes.height, null);
        this.panelNumpad.repaint();
        this.panelProductoPreview.repaint();
    }
}
