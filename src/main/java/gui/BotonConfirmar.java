package gui;

import backend.Comprador;
import backend.Expendedor;
import backend.Moneda;
import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es el botón para confirmar la compra.
 */
public class BotonConfirmar extends JButton implements ActionListener {
    /**
     * Panel del Comprador que se encuentra a la izquierda del GUI.
     */
    private final PanelComprador panelComprador;
    /**
     * Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     */
    private final PanelExpendedor panelExpendedor;
    /**
     * Es el Numpad para seleccionar el producto.
     */
    private final PanelPopupNumpad panelPopupNumpad;
    /**
     * Es el panel donde se ve el producto seleccionado.
     */
    private final PanelExpendedorProductoPreview panelProductoPreview;
    /**
     * Es el botón que activa el popup del Numpad del expendedor.
     */
    private final PanelExpendedorNumpad panelExpendedorNumpad;

    /**
     * Es el botón para confirmar la compra.
     *
     * @param panelComprador Panel del Comprador que se encuentra a la izquierda del GUI.
     */
    public BotonConfirmar(PanelComprador panelComprador) {
        super();

        this.panelComprador = panelComprador;
        this.panelExpendedor = panelComprador.getPanelExpendedor();
        this.panelPopupNumpad = panelComprador.getPanelPopupNumpad();
        this.panelProductoPreview = panelExpendedor.getPanelProductoPreview();
        this.panelExpendedorNumpad = panelExpendedor.getPanelNumpad();

        this.setOpaque(false);
        this.setBackground(Util.color("#000000", 0));
        this.setBorder(null);
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelPopupNumpad.toggleAbierto();
        this.panelProductoPreview.setImagenPreview(null);

        final TipoProductos tipo = this.panelPopupNumpad.getTipoSeleccionado();
        final PanelMoneda panelMoneda = this.panelComprador.getPanelPopupInsertarMoneda().getPanelMonedaSeleccionado();
        this.panelExpendedor.comprarProducto(tipo, panelMoneda != null ? panelMoneda.getMoneda() : null);

        if (panelMoneda != null) panelMoneda.setSeleccionado(false);

        this.panelPopupNumpad.repaint();
        this.panelExpendedorNumpad.repaint();
        this.panelProductoPreview.repaint();
    }
}
