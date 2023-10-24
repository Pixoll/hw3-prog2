package gui;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {
    private final PanelComprador panelComprador;

    private final PanelExpendedor panelExpendedor;

    public PanelPrincipal(int width, int height) {
        this.setLayout(null);

        this.panelComprador = new PanelComprador(this);
        this.panelExpendedor = new PanelExpendedor(this);

        this.panelComprador.setBounds(0, 0, width, height);
        this.panelExpendedor.setBounds(0, 0, 500, height);

        this.add(this.panelComprador);
        this.add(this.panelExpendedor);

        this.setBackground(Util.parseColor("#cde5b3"));
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
        this.panelComprador.repaint();
        this.panelExpendedor.repaint();
    }
}
