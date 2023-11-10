package gui;

import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

/**
 * Son las fuentes utilizadas.
 */
public enum Fuentes {
    PIXELOID_SANS("/fonts/PixeloidSans.ttf", Font.TRUETYPE_FONT);

    /**
     * Fuente utilizada.
     */
    private Font fuente;

    Fuentes(String path, int tipo) {
        final InputStream fuenteStream = Objects.requireNonNull(Fuentes.class.getResourceAsStream(path));
        try {
            this.fuente = Font.createFont(tipo, fuenteStream);
        } catch (Exception ignored) {
            this.fuente = null;
        }
    }

    /**
     * Da la fuente.
     *
     * @param size Tamaño de la fuente.
     * @return La fuente con su nuevo tamaño.
     */
    public Font getFuente(float size) {
        return this.fuente.deriveFont(size);
    }
}
