package gui;

import javax.swing.*;

public class PanelExpendedor extends JPanel {
    private final PanelPrincipal panelPrincipal;

    public PanelExpendedor(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.setBackground(Util.parseColor("#000000", 0));
    }
}