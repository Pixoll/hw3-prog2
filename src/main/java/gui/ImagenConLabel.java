package gui;

import javax.swing.*;
import java.awt.*;

public class ImagenConLabel extends JLabel {
    private final ImagenRecurso imagenRecurso;
    private final JLabel texto;

    public ImagenConLabel(ImagenRecurso imagenRecurso) {
        super(imagenRecurso.getIcon(), SwingConstants.CENTER);

        this.imagenRecurso = imagenRecurso;
        this.setSize(this.getPreferredSize());

        this.texto = new JLabel();
        this.texto.setOpaque(true);
        this.add(this.texto);
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
