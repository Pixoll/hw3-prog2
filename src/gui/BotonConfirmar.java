package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonConfirmar extends JButton implements ActionListener {
    private final PanelPopupNumpad panelPopupNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final PanelExpendedorNumpad panelExpendedorNumpad;

    public BotonConfirmar(
            PanelPopupNumpad panelPopupNumpad,
            PanelExpendedorProductoPreview panelProductoPreview,
            PanelExpendedorNumpad panelExpendedorNumpad
    ) {
        super();

        this.panelPopupNumpad = panelPopupNumpad;
        this.panelProductoPreview = panelProductoPreview;
        this.panelExpendedorNumpad = panelExpendedorNumpad;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelPopupNumpad.toggleNumpadAbierto();
        this.panelExpendedorNumpad.toggleNumpadAbierto();
        this.panelProductoPreview.setImagenPreview(null);

        this.panelPopupNumpad.repaint();
        this.panelExpendedorNumpad.repaint();
        this.panelProductoPreview.repaint();
    }
}
