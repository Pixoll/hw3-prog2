package gui;

import java.awt.*;

public class Util {
    public static Color color(String hexColor, int alpha) {
        final int offset = hexColor.startsWith("#") ? 1 : 0;
        final int r = Integer.parseInt(hexColor.substring(offset, offset + 2), 16);
        final int g = Integer.parseInt(hexColor.substring(offset + 2, offset + 4), 16);
        final int b = Integer.parseInt(hexColor.substring(offset + 4, offset + 6), 16);
        return new Color(r, g, b, alpha);
    }

    public static Color color(String hexColor) {
        return Util.color(hexColor, 255);
    }

    public static void setTimeout(Runnable funcion, long delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (Exception error) {
                error.printStackTrace();
            }

            funcion.run();
        }).start();
    }
}
