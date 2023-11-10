package gui;

import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonNumpad extends JButton implements ActionListener {
    private final PanelPopupNumpad panelPopupNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final TipoProductos tipo;

    public BotonNumpad(TipoProductos tipo, PanelPopupNumpad panelPopupNumpad, PanelExpendedorProductoPreview panelProductoPreview) {
        super();

        this.panelPopupNumpad = panelPopupNumpad;
        this.panelProductoPreview = panelProductoPreview;
        this.tipo = tipo;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelPopupNumpad.setTipoSeleccionado(this.tipo);
        this.panelProductoPreview.setImagenPreview(ImagenRecurso.getImagenProducto(this.tipo).getImagen());
        this.panelProductoPreview.repaint();
    }
}
