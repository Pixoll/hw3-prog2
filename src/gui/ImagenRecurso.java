package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public enum ImagenRecurso {
    ICONO("/images/icon.png"),
    FONDO("/images/background.png"),
    MAQUINA("/images/machine.png"),
    NUMPAD("/images/numpad.png"),
    NUMPAD_BORDE("/images/numpad_highlight.png"),
    NUMPAD_SELECCIONADO("/images/numpad_selected.png"),
    NUMPAD_SELECCIONADO_BORDE("/images/numpad_selected_highlight.png"),
    COCACOLA("/images/cocacola.png", TipoProductos.COCA_COLA),
    FANTA("/images/fanta.png", TipoProductos.FANTA),
    SPRITE("/images/sprite.png", TipoProductos.SPRITE),
    SNICKERS("/images/snickers.png", TipoProductos.SNICKERS),
    SUPER8("/images/super8.png", TipoProductos.SUPER8),
    MONEDA100("/images/moneda100.gif", new Moneda100()),
    MONEDA500("/images/moneda500.gif", new Moneda500()),
    MONEDA1000("/images/moneda1000.gif", new Moneda1000()),
    MONEDA1500("/images/moneda1500.gif", new Moneda1500()),
    ERROR("/images/error.png");

    private final Image imagen;
    private TipoProductos tipoProducto;
    private Moneda moneda;

    ImagenRecurso(String path) {
        final URL iconPath = Objects.requireNonNull(ImagenRecurso.class.getResource(path));
        this.imagen = new ImageIcon(iconPath).getImage();
        this.tipoProducto = null;
        this.moneda = null;
    }

    ImagenRecurso(String path, TipoProductos tipoProducto) {
        this(path);
        this.tipoProducto = tipoProducto;
    }

    ImagenRecurso(String path, Moneda moneda) {
        this(path);
        this.moneda = moneda;
    }

    public Image getImagen() {
        return this.imagen;
    }

    public static Image getImagenProducto(TipoProductos tipo) {
        if (tipo == null) return ImagenRecurso.ERROR.getImagen();
        return Arrays.stream(ImagenRecurso.values())
                .filter(imagenRecurso -> imagenRecurso.tipoProducto == tipo)
                .map(ImagenRecurso::getImagen)
                .findFirst()
                .orElse(null);
    }

    public static Image getImageMoneda(Moneda moneda) {
        if (moneda == null) return ImagenRecurso.ERROR.getImagen();
        return Arrays.stream(ImagenRecurso.values())
                .filter(imagenRecurso -> imagenRecurso.moneda.getValor() == moneda.getValor())
                .map(ImagenRecurso::getImagen)
                .findFirst()
                .orElse(null);
    }
}
