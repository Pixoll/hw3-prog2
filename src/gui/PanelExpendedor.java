package gui;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private final PanelPrincipal panelPrincipal;
    private final Image imagenMaquina;
    private final Rectangle bordes;
    private boolean bordesCalculados;

    public PanelExpendedor(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.imagenMaquina = ImagenRecurso.MAQUINA.getImagen();
        this.bordes = new Rectangle();
        this.bordesCalculados = false;

        this.setLayout(null);
        this.setBackground(Util.parseColor("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());
    }

    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final int panelPrincipalWidth = this.panelPrincipal.getBounds().width;
        final int panelPrincipalHeight = this.panelPrincipal.getBounds().height;
        final int maquinaWidth = this.imagenMaquina.getWidth(null);
        final int maquinaHeight = this.imagenMaquina.getHeight(null);

        final float maquinaScaling = (float) (panelPrincipalHeight * 0.95) / maquinaHeight;
        this.bordes.width = (int) (maquinaWidth * maquinaScaling);
        this.bordes.height = (int) (maquinaHeight * maquinaScaling);
        this.bordes.y = (panelPrincipalHeight - this.bordes.height) / 2;
        this.bordes.x = panelPrincipalWidth - this.bordes.width - (this.bordes.y * 2);

        this.bordesCalculados = true;
    }

    public Rectangle getBordes() {
        return this.bordes;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelPrincipal.getBounds());
        this.calcularBordes();
        super.paint(graphics);

        graphics.drawImage(this.imagenMaquina, this.bordes.x, this.bordes.y, this.bordes.width, this.bordes.height, null);
    }
}
