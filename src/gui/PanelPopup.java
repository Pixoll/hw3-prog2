package gui;

import javax.swing.*;
import java.awt.*;

public abstract class PanelPopup extends JPanel {
    protected final PanelComprador panelComprador;
    protected final Rectangle bordes;
    protected boolean bordesCalculados;
    private boolean abierto;
    protected boolean popupLimpiado;

    public PanelPopup(PanelComprador panelComprador) {
        this.panelComprador = panelComprador;
        this.bordes = new Rectangle();
        this.bordesCalculados = false;
        this.abierto = false;
        this.popupLimpiado = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelComprador.getBounds());
    }

    public boolean isAbierto() {
        return this.abierto;
    }

    public void toggleAbierto() {
        this.abierto = !this.abierto;
    }

    protected abstract void calcularBordes();
}
