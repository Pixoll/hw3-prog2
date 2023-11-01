package gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public enum ImagenRecurso {
    ICONO("/images/icon.png"),
    FONDO("/images/background.png"),
    MAQUINA("/images/machine.png");

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
