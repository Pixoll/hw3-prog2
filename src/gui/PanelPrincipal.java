package gui;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private final PanelComprador panelComprador;
    private final PanelExpendedor panelExpendedor;
    private final Image fondo;

    public PanelPrincipal() {
        this.setLayout(null);

        this.panelComprador = new PanelComprador(this);
        this.panelExpendedor = new PanelExpendedor(this);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);

        this.fondo = new ImageIcon(Util.getRecursoPath(
                PanelPrincipal.class, "/images/background.jpg")
        ).getImage();
    }

    public PanelComprador getPanelComprador() {
        return this.panelComprador;
    }

    public PanelExpendedor getPanelExpendedor() {
        return this.panelExpendedor;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        final float fondoScaling = (float) (this.getHeight()) / this.fondo.getHeight(null);
        final int fondoWidth = (int) (this.fondo.getWidth(null) * fondoScaling);
        final int fondoHeight = (int) (this.fondo.getHeight(null) * fondoScaling);
        int fondoX = 0;
        while (fondoX < this.getWidth()) {
            graphics.drawImage(this.fondo, fondoX, 0, fondoWidth, fondoHeight, null);
            fondoX += fondoWidth;
        }

        this.panelComprador.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.panelExpendedor.setBounds(0, 0, this.getWidth() / 2, this.getHeight());

        this.panelComprador.repaint();
        this.panelExpendedor.repaint();
    }
}
