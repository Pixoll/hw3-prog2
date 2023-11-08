package gui;

import javax.swing.*;

public class PanelTexto extends JTextPane {
    PanelTexto(String texto, String colorTexto, String colorFondo) {
        this.setText(texto);
        this.setBackground(Util.color(colorFondo));
        this.setForeground(Util.color(colorTexto));
        this.setBorder(null);
        this.setEditable(false);
        this.setFocusable(false);
    }
}
