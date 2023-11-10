package gui;

import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es un botón del numpad.
 */
public class BotonNumpad extends JButton implements ActionListener {
    /**
     * Es el Numpad para seleccionar el producto.
     */
    private final PanelPopupNumpad panelPopupNumpad;
    /**
     * Es el panel donde se ve el producto seleccionado.
     */
    private final PanelExpendedorProductoPreview panelProductoPreview;
    /**
     * Es el tipo del producto correspondiente al botón.
     */
    private final TipoProductos tipo;

    /**
     * Es un botón del numpad.
     *
     * @param tipo                 Tipo del producto correspondiente al botón.
     * @param panelPopupNumpad     Numpad para seleccionar el producto.
     * @param panelProductoPreview Panel donde se ve el producto seleccionado.
     */
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
