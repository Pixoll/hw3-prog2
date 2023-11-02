package gui;

import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private final PanelPrincipal panelPrincipal;
    private final Rectangle bordes;
    private boolean bordesCalculados;

    public PanelComprador(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.bordes = new Rectangle();
        this.bordesCalculados = false;

        this.setLayout(null);
        this.setBackground(Util.parseColor("#000000", 0));
        this.setBounds(panelPrincipal.getBounds());
    }

    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final Rectangle expendedorBordes = this.panelPrincipal.getPanelExpendedor().getBordes();

        this.bordes.x = expendedorBordes.y * 2;
        this.bordes.y = expendedorBordes.y;
        this.bordes.width = expendedorBordes.x - (this.bordes.x * 2);
        this.bordes.height = expendedorBordes.height;

        this.bordesCalculados = true;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelPrincipal.getBounds());
        this.calcularBordes();
        super.paint(graphics);

        // TODO: Solo para ver dónde está, remover después
        graphics.setColor(Util.parseColor("#ffff80", 64));
        graphics.fillRect(this.bordes.x, this.bordes.y, this.bordes.width, this.bordes.height);
    }
}
