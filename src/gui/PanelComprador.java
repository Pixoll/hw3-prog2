package gui;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private final PanelPrincipal panelPrincipal;
    private final PanelPopupNumpad panelPopupNumpad;
    private final Rectangle bordes;
    private final Image imagenFondo;
    private boolean bordesCalculados;

    public PanelComprador(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.bordes = new Rectangle();
        this.bordesCalculados = false;
        this.imagenFondo = ImagenRecurso.FONDO.getImagen();

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());

        this.panelPopupNumpad = new PanelPopupNumpad(this);
        this.add(this.panelPopupNumpad);
    }

    public PanelPopupNumpad getPanelPopupNumpad() {
        return this.panelPopupNumpad;
    }

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

    public Rectangle getBordes() {
        return this.bordes;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        final Rectangle bordesFondo = this.panelPrincipal.getBordesFondo();
        int fondoX = 0;
        while (fondoX < this.getWidth()) {
            graphics.drawImage(this.imagenFondo, fondoX, 0, bordesFondo.width, bordesFondo.height, null);
            fondoX += bordesFondo.width;
        }
    }
}
