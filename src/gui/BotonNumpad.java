package gui;

import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonNumpad extends JButton implements ActionListener {
    private final PanelExpendedor panelExpendedor;
    private final PanelPopupNumpad panelPopupNumpad;
    private final TipoProductos tipo;

    public BotonNumpad(PanelPopupNumpad panelPopupNumpad, PanelExpendedor panelExpendedor, TipoProductos tipo) {
        super();

        this.panelPopupNumpad = panelPopupNumpad;
        this.panelExpendedor = panelExpendedor;
        this.tipo = tipo;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("click " + this.tipo);
    }
}
