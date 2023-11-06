package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

public class PanelExpendedorSeleccionable extends JPanel implements MouseListener {
    protected final PanelComprador panelComprador;
    protected final PanelExpendedor panelExpendedor;
    private PanelPopup panelPopup;
    private final Function<PanelExpendedor, Rectangle> calculadorBorde;
    private final Image imagenPanel;
    private final Image imagenPanelBorde;
    private final Image imagenPanelSeleccionado;
    private final Image imagenPanelSeleccionadoBorde;
    private boolean mouseEnPanel;

    public PanelExpendedorSeleccionable(
            PanelExpendedor panelExpendedor,
            Function<PanelExpendedor, Rectangle> calculadorBorde,
            ImagenRecurso imagenPanel,
            ImagenRecurso imagenPanelBorde,
            ImagenRecurso imagenPanelSeleccionado,
            ImagenRecurso imagenPanelSeleccionadoBorde
    ) {
        this.panelExpendedor = panelExpendedor;
        this.panelComprador = panelExpendedor.getPanelPrincipal().getPanelComprador();
        this.panelPopup = null;
        this.calculadorBorde = calculadorBorde;
        this.imagenPanel = imagenPanel.getImagen();
        this.imagenPanelBorde = imagenPanelBorde.getImagen();
        this.imagenPanelSeleccionado = imagenPanelSeleccionado.getImagen();
        this.imagenPanelSeleccionadoBorde = imagenPanelSeleccionadoBorde.getImagen();
        this.mouseEnPanel = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelExpendedor.getBounds());

        this.addMouseListener(this);
    }

    public void setPanelPopup(PanelPopup panelPopup) {
        this.panelPopup = panelPopup;
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.calculadorBorde.apply(this.panelExpendedor));
        super.paint(graphics);

        final Rectangle bordes = this.getBounds();
        final Image numpad = this.panelPopup.isAbierto() && this.mouseEnPanel ? this.imagenPanelSeleccionadoBorde
                : this.panelPopup.isAbierto() ? this.imagenPanelSeleccionado
                : this.mouseEnPanel ? this.imagenPanelBorde
                : this.imagenPanel;

        graphics.clearRect(0, 0, bordes.width, bordes.height);
        graphics.drawImage(numpad, 0, 0, bordes.width, bordes.height, null);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        for (PanelPopup panelPopup : this.panelComprador.getPanelesPopup()) {
            if (panelPopup.isAbierto() && panelPopup != this.panelPopup) {
                panelPopup.toggleAbierto();
                panelPopup.repaint();
                panelPopup.getPanelExpendedorSeleccionable().repaint();
            }
        }
        this.panelPopup.toggleAbierto();
        this.panelPopup.repaint();
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.mouseEnPanel = true;
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.mouseEnPanel = false;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }
}
