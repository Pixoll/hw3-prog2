package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonInsertarMoneda extends JButton implements ActionListener {
    private final PanelMoneda panelMoneda;
    private final PanelPopupInsertarMoneda panelPopup;

    public BotonInsertarMoneda(PanelMoneda panelMoneda, PanelPopupInsertarMoneda panelPopup) {
        super();

        this.panelMoneda = panelMoneda;
        this.panelPopup = panelPopup;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    public PanelMoneda getPanelMoneda() {
        return this.panelMoneda;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (PanelMoneda panelMoneda : this.panelPopup.getPanelesMoneda()) {
            panelMoneda.setSeleccionado(false);
        }
        this.panelMoneda.setSeleccionado(true);
        this.panelPopup.repaint();
    }
}
