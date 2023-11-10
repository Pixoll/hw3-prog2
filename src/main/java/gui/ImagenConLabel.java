package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Es un panel con imagen de fondo y texto.
 */
public class ImagenConLabel extends JLabel {
    /**
     * Es la imagen del panel.
     */
    private final ImagenRecurso imagenRecurso;
    /**
     * Es el panel del texto que tiene la imagen.
     */
    private final JLabel texto;

    /**
     * Es un panel con imagen de fondo y texto.
     * @param imagenRecurso La imagen del panel.
     */
    public ImagenConLabel(ImagenRecurso imagenRecurso) {
        super(imagenRecurso.getIcon(), SwingConstants.CENTER);

        this.imagenRecurso = imagenRecurso;
        this.setSize(this.getPreferredSize());

        this.texto = new JLabel();
        this.texto.setOpaque(true);
        this.add(this.texto);
    }

    /**
     * Da la imagen del panel.
     * @return La imagen del panel.
     */
    public ImagenRecurso getImagenRecurso() {
        return this.imagenRecurso;
    }

    /**
     * Da el texto de la imagen.
     * @return El texto de la imagen.
     */
    public String getTexto() {
        return this.texto.getText();
    }

    @Override
    public void setBounds(Rectangle rectangle) {
        super.setBounds(rectangle);
        this.setIcon(new ImageIcon(imagenRecurso.getResized(rectangle.width, rectangle.height)));
    }

    public void setTexto(String texto) {
        this.texto.setText(texto);
        final Dimension sizeTexto = this.texto.getPreferredSize();
        this.texto.setSize(sizeTexto);

        final int textoX = (this.getWidth() - sizeTexto.width) / 2;
        final int textoY = (int) (this.getHeight() - (sizeTexto.height * 1.2));
        this.texto.setLocation(textoX, textoY);
    }

    public void setColoresTexto(String colorTexto, String colorFondo) {
        this.texto.setForeground(Util.color(colorTexto));
        this.texto.setBackground(Util.color(colorFondo));
    }

    public void setTextoFuente(Font fuente) {
        this.texto.setFont(fuente);
        this.texto.setSize(this.texto.getPreferredSize());
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.texto.repaint();
    }
}
