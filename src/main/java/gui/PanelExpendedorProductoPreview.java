package gui;

import javax.swing.*;
import java.awt.*;

public class PanelExpendedorProductoPreview extends JPanel {
    private final PanelExpendedor panelExpendedor;
    private Image imagenPreview;

    public PanelExpendedorProductoPreview(PanelExpendedor panelExpendedor) {
        this.panelExpendedor = panelExpendedor;
        this.imagenPreview = null;

        this.setLayout(null);
        this.setBackground(Util.color("#99cdff"));
        this.setBounds(panelExpendedor.getBounds());
    }

    public void setImagenPreview(Image imagenPreview) {
        this.imagenPreview = imagenPreview;
    }

    private Rectangle calcularBordesImagenPreview() {
        final int y = (int) (this.getHeight() * 0.1);
        final float imagenPreviewScale = (float) (this.getHeight() - (y * 2)) / this.imagenPreview.getHeight(null);
        final int height = (int) (this.imagenPreview.getHeight(null) * imagenPreviewScale);
        final int width = (int) (this.imagenPreview.getWidth(null) * imagenPreviewScale);
        final int x = (this.getWidth() - width) / 2;

        return new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics graphics) {
        this.setBounds(this.panelExpendedor.getPreviewBordes());
        super.paint(graphics);

        if (this.imagenPreview != null) {
            final Rectangle bordes = this.calcularBordesImagenPreview();
            graphics.drawImage(this.imagenPreview, bordes.x, bordes.y, bordes.width, bordes.height, null);
            return;
        }

        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
