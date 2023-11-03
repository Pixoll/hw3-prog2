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
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final Image imagenPreview = this.tipo == TipoProductos.COCA_COLA ? ImagenRecurso.COCACOLA.getImagen()
                : this.tipo == TipoProductos.FANTA ? ImagenRecurso.FANTA.getImagen()
                : this.tipo == TipoProductos.SPRITE ? ImagenRecurso.SPRITE.getImagen()
                : this.tipo == TipoProductos.SNICKERS ? ImagenRecurso.SNICKERS.getImagen()
                : this.tipo == TipoProductos.SUPER8 ? ImagenRecurso.SUPER8.getImagen()
                : ImagenRecurso.ERROR.getImagen();

        this.panelProductoPreview.setImagenPreview(imagenPreview);

        this.panelProductoPreview.repaint();
    }
}
