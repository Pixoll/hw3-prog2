package gui;

import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelPopupNumpad extends JPanel {
    private final PanelComprador panelComprador;
    private final Image imagenNumpad;
    private final Rectangle bordes;
    private boolean bordesCalculados;
    private boolean numpadAbierto;
    private boolean numpadLimpiado;
    private final ArrayList<BotonNumpad> botonesProductos;
    private boolean botonesAdded;

    public PanelPopupNumpad(PanelComprador panelComprador) {
        this.panelComprador = panelComprador;
        this.imagenNumpad = ImagenRecurso.NUMPAD.getImagen();
        this.bordes = new Rectangle();
        this.bordesCalculados = false;
        this.numpadAbierto = false;
        this.numpadLimpiado = false;
        this.botonesProductos = new ArrayList<>();
        this.botonesAdded = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelComprador.getBounds());

        for (int i = 0; i < 12; i++) {
            final TipoProductos tipo = TipoProductos.valueOf(i);
            this.botonesProductos.add(new BotonNumpad(
                    this, panelComprador.getPanelPrincipal().getPanelExpendedor(), tipo
            ));
        }
    }

    public void toggleNumpadAbierto() {
        this.numpadAbierto = !this.numpadAbierto;
    }

    private void calcularBordes() {
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

        final int totalWidth = this.bordes.width;
        final int botonSize = (int) (totalWidth * (4f / 13));
        final int botonOffset = (totalWidth - botonSize * 3) / 2;
        int i = 0;
        for (BotonNumpad boton : this.botonesProductos) {
            final int x = botonOffset + (i % 3) * botonSize;
            final int y = botonOffset + (i / 3) * botonSize;
            boton.setBounds(x, y, botonSize, botonSize);
            this.add(boton);
            i++;
        }

        this.botonesAdded = true;
    }

    private void toggleBotonesProductos(boolean estado) {
        for (BotonNumpad boton : this.botonesProductos) {
            boton.setEnabled(estado);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        this.addBotones();
        super.paint(graphics);

        if (this.numpadAbierto) {
            graphics.drawImage(this.imagenNumpad, 0, 0, this.bordes.width, this.bordes.height, null);
            this.toggleBotonesProductos(true);
            this.numpadLimpiado = false;
            return;
        }

        if (!this.numpadLimpiado) {
            this.numpadLimpiado = true;
            this.toggleBotonesProductos(false);
            this.panelComprador.repaint();
        }
    }
}
