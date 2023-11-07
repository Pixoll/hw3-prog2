package gui;

import backend.TipoProductos;

import java.awt.*;
import java.util.ArrayList;

public class PanelPopupNumpad extends PanelPopup {
    private PanelExpendedorProductoPreview panelProductoPreview;
    private final Image imagenNumpad;
    private final ArrayList<BotonNumpad> botonesProductos;
    private BotonConfirmar botonConfirmar;
    private boolean botonesAdded;

    public PanelPopupNumpad(PanelComprador panelComprador) {
        super(panelComprador);

        this.panelProductoPreview = null;
        this.imagenNumpad = ImagenRecurso.NUMPAD.getImagen();
        this.botonesProductos = new ArrayList<>();
        this.botonConfirmar = null;
        this.botonesAdded = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelComprador.getBounds());
    }

    public void setPanelProductoPreview(PanelExpendedorProductoPreview panelProductoPreview) {
        this.panelProductoPreview = panelProductoPreview;
    }

    public PanelExpendedorProductoPreview getPanelProductoPreview() {
        return this.panelProductoPreview;
    }

    @Override
    protected void calcularBordes() {
        if (this.bordesCalculados) return;

        final Rectangle compradorBordes = this.panelComprador.getBordes();
        final int popupHeight = (int) (compradorBordes.height / 1.5);
        final int popupWidth = (int) (
                ((float) (popupHeight) / this.imagenNumpad.getHeight(null))
                        * this.imagenNumpad.getWidth(null)
        );

        this.bordes.x = compradorBordes.x + (compradorBordes.y * 2);
        this.bordes.y = compradorBordes.y * 2;
        this.bordes.width = popupWidth;
        this.bordes.height = popupHeight;

        this.setBounds(this.bordes);

        this.bordesCalculados = true;
    }

    private void addBotones() {
        if (this.botonesAdded) return;

        for (int i = 0; i < 12; i++) {
            final TipoProductos tipo = TipoProductos.valueOf(i);
            this.botonesProductos.add(new BotonNumpad(tipo, this.panelProductoPreview));
        }

        this.botonConfirmar = new BotonConfirmar(this);

        final int totalWidth = this.bordes.width;
        final int botonSize = (int) (totalWidth * (2f / 7));
        final int botonOffset = (totalWidth - botonSize * 3) / 2;

        int i = 0;
        for (BotonNumpad boton : this.botonesProductos) {
            final int x = botonOffset + (i % 3) * botonSize;
            final int y = botonOffset + (i / 3) * botonSize;
            boton.setBounds(x, y, botonSize, botonSize);
            this.add(boton);
            i++;
        }

        this.botonConfirmar.setBounds(
                botonOffset, this.bordes.height - botonOffset - botonSize,
                botonSize * 3, botonSize
        );
        this.add(this.botonConfirmar);

        this.botonesAdded = true;
    }

    private void toggleBotonesProductos(boolean estado) {
        for (BotonNumpad boton : this.botonesProductos) {
            boton.setEnabled(estado);
        }
        this.botonConfirmar.setEnabled(estado);
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        this.addBotones();
        super.paint(graphics);

        if (this.isAbierto()) {
            graphics.drawImage(this.imagenNumpad, 0, 0, this.bordes.width, this.bordes.height, null);
            this.toggleBotonesProductos(true);
            this.popupLimpiado = false;
            return;
        }

        if (!this.popupLimpiado) {
            this.popupLimpiado = true;
            this.toggleBotonesProductos(false);
            this.panelComprador.repaint();
        }
    }
}
