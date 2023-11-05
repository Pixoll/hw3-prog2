package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonConfirmar extends JButton implements ActionListener {
    private final PanelPopupNumpad panelPopupNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;

    public BotonConfirmar(PanelPopupNumpad panelPopupNumpad, PanelExpendedorProductoPreview panelProductoPreview) {
        super();

        this.panelPopupNumpad = panelPopupNumpad;
        this.panelProductoPreview = panelProductoPreview;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelProductoPreview.setImagenPreview(null);
        this.panelProductoPreview.repaint();
    }
}
