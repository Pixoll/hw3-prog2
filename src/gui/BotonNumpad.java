package gui;

import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonNumpad extends JButton implements ActionListener {
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final TipoProductos tipo;

    public BotonNumpad(TipoProductos tipo, PanelExpendedorProductoPreview panelProductoPreview) {
        super();

        this.panelProductoPreview = panelProductoPreview;
        this.tipo = tipo;

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelProductoPreview.setImagenPreview(ImagenRecurso.getImagenProducto(this.tipo));
        this.panelProductoPreview.repaint();
    }
}
