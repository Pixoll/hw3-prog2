package gui;

import java.awt.*;
import java.awt.image.ImageObserver;

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

    public static void drawImage(Graphics graphics, Image imagen, Rectangle bordes, ImageObserver observer) {
        graphics.drawImage(imagen, bordes.x, bordes.y, bordes.width, bordes.height, observer);
    }

    public static void drawImage(Graphics graphics, Image imagen, Rectangle bordes) {
        Util.drawImage(graphics, imagen, bordes, null);
    }

    public static void drawImage(Graphics graphics, Image imagen, Dimension size) {
        graphics.drawImage(imagen, 0, 0, size.width, size.height, null);
    }

    public static void fillRect(Graphics graphics, Rectangle bordes, Color color) {
        graphics.setColor(color);
        graphics.fillRect(bordes.x, bordes.y, bordes.width, bordes.height);
    }

    public static void fillRect(Graphics graphics, Dimension size, Color color) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, size.width, size.height);
    }
}
