package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es el botón que inserta la moneda.
 */
public class BotonInsertarMoneda extends JButton implements ActionListener {
    /**
     * Panel que contiene la imagen y moneda del botón.
     */
    private final PanelMoneda panelMoneda;
    /**
     * Es el panel donde se selecciona la moneda a usar.
     */
    private final PanelPopupInsertarMoneda panelPopup;

    /**
     * Es el botón que inserta la moneda.
     *
     * @param panelMoneda Panel que contiene la imagen y moneda del botón.
     * @param panelPopup  Es el panel donde se selecciona la moneda a usar.
     */
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

    /**
     * Da el panel que contiene la imagen y moneda del botón.
     *
     * @return El panel que contiene la imagen y moneda del botón.
     */
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
