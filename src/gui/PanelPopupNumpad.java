package gui;

import javax.swing.*;
import java.awt.*;

public class PanelPopupNumpad extends JPanel {
    private final PanelComprador panelComprador;
    private final Image imagenNumpad;
    private final Rectangle bordes;
    private boolean bordesCalculados;
    private boolean numpadAbierto;
    private boolean numpadLimpiado;

    public PanelPopupNumpad(PanelComprador panelComprador) {
        this.panelComprador = panelComprador;
        this.imagenNumpad = ImagenRecurso.NUMPAD.getImagen();
        this.bordes = new Rectangle();
        this.bordesCalculados = false;
        this.numpadAbierto = false;
        this.numpadLimpiado = false;

        this.setLayout(null);
        this.setBackground(Util.parseColor("#000000", 0));
        this.setBounds(panelComprador.getBounds());
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

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        if (this.numpadAbierto) {
            graphics.drawImage(this.imagenNumpad, 0, 0, this.bordes.width, this.bordes.height, null);
            this.numpadLimpiado = false;
            return;
        }

        if (!this.numpadLimpiado) {
            this.numpadLimpiado = true;
            this.panelComprador.repaint();
        }
    }
}
