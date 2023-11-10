package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Function;

/**
 * Es el panel donde aparece el producto obtenido.
 */
public class PanelExpendedorObtenerProducto extends JPanel implements MouseListener {
    /**
     * Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     */
    protected final PanelExpendedor panelExpendedor;
    /**
     * Panel del Comprador que se encuentra a la izquierda del GUI.
     */
    protected final PanelComprador panelComprador;
    /**
     * Es la imagen del panel.
     */
    private final Image imagenPanel;
    /**
     * Es la imagen con los bordes del panel.
     */
    private final Image imagenPanelBorde;
    /**
     * Si es que el mouse esta en el panel.
     */
    private boolean mouseEnPanel;

    /**
     * Es el panel donde aparece el producto obtenido.
     * @param panelExpendedor Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     * @param imagenPanel
     * @param imagenPanelBorde
     */
    public PanelExpendedorObtenerProducto(PanelExpendedor panelExpendedor, ImagenRecurso imagenPanel, ImagenRecurso imagenPanelBorde) {
        this.panelExpendedor = panelExpendedor;
        this.panelComprador = panelExpendedor.getPanelComprador();
        this.imagenPanel = imagenPanel.getImagen();
        this.imagenPanelBorde = imagenPanelBorde.getImagen();
        this.mouseEnPanel = false;

        this.setLayout(null);
        this.setBackground(Util.color("#000000", 0));
        this.setBounds(panelExpendedor.getBounds());

        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelExpendedor.getObtenerProductoBordes());
        super.paint(graphics);

        final Image numpad = this.mouseEnPanel ? this.imagenPanelBorde : this.imagenPanel;
        Util.drawImage(graphics, numpad, this.getBounds().getSize());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.panelComprador.repaint();
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
