package gui;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

public enum Fuentes {
    PIXELOID_SANS("/fonts/PixeloidSans.ttf", Font.TRUETYPE_FONT);

    private Font fuente;

    Fuentes(String path, int tipo) {
        final InputStream fuenteStream = Objects.requireNonNull(Fuentes.class.getResourceAsStream(path));
        try {
            this.fuente = Font.createFont(tipo, fuenteStream);
        } catch (Exception ignored) {
            this.fuente = null;
        }
    }

    public Font getFuente(float size) {
        return this.fuente.deriveFont(size);
    }
}
