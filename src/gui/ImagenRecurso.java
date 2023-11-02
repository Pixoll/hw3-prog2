package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public enum ImagenRecurso {
    ICONO("/images/icon.png"),
    FONDO("/images/background.png"),
    MAQUINA("/images/machine.png"),
    COCACOLA("/images/cocacola.png"),
    SPRITE("/images/sprite.png"),
    FANTA("/images/fanta.png"),
    SNICKERS("/images/snickers.png"),
    SUPER8("/images/super8.png"),
    MONEDA100("/images/moneda100.png"),
    MONEDA500("/images/moneda500.png"),
    MONEDA1000("/images/moneda1000.png"),
    MONEDA1500("/images/moneda1500.png");

    private final URL path;
    private final Image imagen;

    ImagenRecurso(String path) {
        this.path = Objects.requireNonNull(ImagenRecurso.class.getResource(path));
        this.imagen = new ImageIcon(this.path).getImage();
    }

    public URL getPath() {
        return this.path;
    }

    public Image getImagen() {
        return this.imagen;
    }
}
