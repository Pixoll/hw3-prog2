package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelExpendedorNumpad extends JPanel implements MouseListener {
    private final PanelExpendedor panelExpendedor;
    private PanelPopupNumpad panelPopupNumpad;
    private final Image imagenNumpad;
    private final Image imagenNumpadBorde;
    private final Image imagenNumpadSeleccionado;
    private final Image imagenNumpadSeleccionadoBorde;
    private boolean mouseEnNumpad;
    private boolean numpadAbierto;

    public PanelExpendedorNumpad(PanelExpendedor panelExpendedor) {
        this.panelExpendedor = panelExpendedor;
        this.imagenNumpad = ImagenRecurso.NUMPAD.getImagen();
        this.imagenNumpadBorde = ImagenRecurso.NUMPAD_BORDE.getImagen();
        this.imagenNumpadSeleccionado = ImagenRecurso.NUMPAD_SELECCIONADO.getImagen();
        this.imagenNumpadSeleccionadoBorde = ImagenRecurso.NUMPAD_SELECCIONADO_BORDE.getImagen();
        this.mouseEnNumpad = false;
        this.numpadAbierto = false;

        this.setLayout(null);
        this.setBackground(Util.parseColor("#000000", 0));
        this.setBounds(panelExpendedor.getBounds());

        this.addMouseListener(this);
    }

    public void setPanelPopupNumpad(PanelPopupNumpad panelPopupNumpad) {
        this.panelPopupNumpad = panelPopupNumpad;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelExpendedor.getNumpadBordes());
        super.paint(graphics);

        final Rectangle bordes = this.getBounds();
        final Image numpad = this.numpadAbierto && this.mouseEnNumpad ? this.imagenNumpadSeleccionadoBorde
                : this.numpadAbierto ? this.imagenNumpadSeleccionado
                : this.mouseEnNumpad ? this.imagenNumpadBorde
                : this.imagenNumpad;

        graphics.clearRect(0, 0, bordes.width, bordes.height);
        graphics.drawImage(numpad, 0, 0, bordes.width, bordes.height, null);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.numpadAbierto = !this.numpadAbierto;
        this.panelPopupNumpad.toggleNumpadAbierto();
        this.panelPopupNumpad.repaint();
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.mouseEnNumpad = true;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.mouseEnNumpad = false;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }
}
