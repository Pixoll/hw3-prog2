package gui;

import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class Util {
    public static <T> URL getRecursoPath(Class<T> referencia, String archivo) {
        return Objects.requireNonNull(referencia.getResource(archivo));
    }

    public static Color parseColor(String hexColor, int alpha) {
        final int offset = hexColor.startsWith("#") ? 1 : 0;
        final int r = Integer.parseInt(hexColor.substring(offset, offset + 2), 16);
        final int g = Integer.parseInt(hexColor.substring(offset + 2, offset + 4), 16);
        final int b = Integer.parseInt(hexColor.substring(offset + 4, offset + 6), 16);
        return new Color(r, g, b, alpha);
    }

    public static Color parseColor(String hexColor) {
        return Util.parseColor(hexColor, 255);
    }
}