package gui;

import javax.swing.*;

public class PanelComprador extends JPanel {
    private final PanelPrincipal panelPrincipal;

    public PanelComprador(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.setBackground(Util.parseColor("#000000", 0));
    }
}
