package gui;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private final PanelComprador panelComprador;
    private final PanelExpendedor panelExpendedor;
    private final Image imagenFondo;
    private final Rectangle bordesFondo;
    private boolean bordesArreglados;

    public PanelPrincipal(int width, int height) {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);

        this.panelComprador = new PanelComprador(this);
        this.panelExpendedor = new PanelExpendedor(this);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);

        this.imagenFondo = ImagenRecurso.FONDO.getImagen();

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        final int fondoWidth = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        final int fondoHeight = (int) (this.imagenFondo.getHeight(null) * fondoScaling);

        this.bordesFondo = new Rectangle(fondoWidth, fondoHeight);
        this.bordesArreglados = false;
    }

    public PanelComprador getPanelComprador() {
        return this.panelComprador;
    }

    public PanelExpendedor getPanelExpendedor() {
        return this.panelExpendedor;
    }

    private void arreglarBordes() {
        if (this.bordesArreglados) return;

        this.setBounds(0, 0, this.getWidth(), this.getHeight());

        final float fondoScaling = (float) (this.getHeight()) / this.imagenFondo.getHeight(null);
        this.bordesFondo.width = (int) (this.imagenFondo.getWidth(null) * fondoScaling);
        this.bordesFondo.height = (int) (this.imagenFondo.getHeight(null) * fondoScaling);

        this.bordesArreglados = true;
    }

    @Override
    public void paint(Graphics graphics) {
        this.arreglarBordes();
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
