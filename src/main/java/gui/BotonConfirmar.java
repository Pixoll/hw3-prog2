package gui;

import backend.Comprador;
import backend.Expendedor;
import backend.Moneda;
import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonConfirmar extends JButton implements ActionListener {
    private final PanelComprador panelComprador;
    private final PanelExpendedor panelExpendedor;
    private final PanelPopupNumpad panelPopupNumpad;
    private final PanelExpendedorProductoPreview panelProductoPreview;
    private final PanelExpendedorSeleccionable panelExpendedorNumpad;

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
