package gui;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private final PanelPrincipal panelPrincipal;
    private final PanelExpendedorNumpad panelNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final PanelExpendedorInsertarMoneda panelInsertarMoneda;
    private final Image imagenMaquina;
    private final Rectangle bordes;
    private final Rectangle numpadBordes;
    private final Rectangle previewBordes;
    private final Rectangle insertarMonedaBordes;
    private boolean bordesCalculados;

    public PanelExpendedor(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.imagenMaquina = ImagenRecurso.MAQUINA.getImagen();
        this.bordes = new Rectangle();
        this.numpadBordes = new Rectangle();
        this.previewBordes = new Rectangle();
        this.insertarMonedaBordes = new Rectangle();
        this.bordesCalculados = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());

        this.panelNumpad = new PanelExpendedorNumpad(this);
        this.panelProductoPreview = new PanelExpendedorProductoPreview(this);
        this.panelInsertarMoneda = new PanelExpendedorInsertarMoneda(this);

        this.add(this.panelNumpad);
        this.add(this.panelProductoPreview);
        this.add(this.panelInsertarMoneda);
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

    public PanelExpendedorInsertarMoneda getPanelInsertarMoneda() {
        return this.panelInsertarMoneda;
    }

    private void calcularBordesPorCoords(Rectangle bordes, float maquinaScaling, int x, int y, int width, int height) {
        bordes.x = this.bordes.x + (int) (x * maquinaScaling);
        bordes.y = this.bordes.y + (int) (y * maquinaScaling);
        bordes.width = (int) (width * maquinaScaling);
        bordes.height = (int) (height * maquinaScaling);
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

        this.calcularBordesPorCoords(this.numpadBordes, maquinaScaling, 203, 204, 56, 96);
        this.calcularBordesPorCoords(this.previewBordes, maquinaScaling, 207, 97, 48, 48);
        this.calcularBordesPorCoords(this.insertarMonedaBordes, maquinaScaling, 205, 156, 52, 40);

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

    public Rectangle getInsertarMonedaBordes() {
        return this.insertarMonedaBordes;
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
    }
}
