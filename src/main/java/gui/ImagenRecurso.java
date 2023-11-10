package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

/**
 * Son las imágenes utilizadas
 */
public enum ImagenRecurso {
    ICONO("/images/icon.png"),
    FONDO("/images/background.png"),
    MAQUINA("/images/machine.png"),
    NUMPAD("/images/numpad.png"),
    NUMPAD_BORDE("/images/numpad_highlight.png"),
    NUMPAD_SELECCIONADO("/images/numpad_selected.png"),
    NUMPAD_SELECCIONADO_BORDE("/images/numpad_selected_highlight.png"),
    INSERTAR_MONEDA("/images/insert_coin.png"),
    INSERTAR_MONEDA_BORDE("/images/insert_coin_highlight.png"),
    INSERTAR_MONEDA_SELECCIONADO("/images/insert_coin_selected.png"),
    INSERTAR_MONEDA_SELECCIONADO_BORDE("/images/insert_coin_selected_highlight.png"),
    BOTON_PUSH("/images/push.png"),
    BOTON_PUSH_BORDE("/images/push_highlight.png"),
    COCACOLA("/images/cocacola.png", TipoProductos.COCA_COLA),
    FANTA("/images/fanta.png", TipoProductos.FANTA),
    SPRITE("/images/sprite.png", TipoProductos.SPRITE),
    SNICKERS("/images/snickers.png", TipoProductos.SNICKERS),
    SUPER8("/images/super8.png", TipoProductos.SUPER8),
    MONEDA100("/images/moneda100.gif", new Moneda100()),
    MONEDA500("/images/moneda500.gif", new Moneda500()),
    MONEDA1000("/images/moneda1000.gif", new Moneda1000()),
    MONEDA1500("/images/moneda1500.gif", new Moneda1500()),
    ERROR("/images/error.png"),
    FLECHA_ARRIBA("/images/arrow.png");

    /**
     * Es el ícono de la imagen.
     */
    private final ImageIcon icon;
    /**
     * Es la imagen.
     */
    private final Image imagen;
    /**
     * Es el tipo de producto asociado a la imagen.
     */
    private TipoProductos tipoProducto;
    /**
     * Es el tipo de moneda asociada a la imagen.
     */
    private Moneda moneda;

    /**
     * Son las imágenes utilizadas
     *
     * @param path La ubicación de la imagen.
     */
    ImagenRecurso(String path) {
        final URL imagenPath = Objects.requireNonNull(ImagenRecurso.class.getResource(path));
        this.icon = new ImageIcon(imagenPath);
        this.imagen = icon.getImage();
        this.tipoProducto = null;
        this.moneda = null;
    }

    /**
     * Son las imágenes utilizadas
     *
     * @param path         La ubicación de la imagen.
     * @param tipoProducto El tipo de producto asociado a la imagen.
     */
    ImagenRecurso(String path, TipoProductos tipoProducto) {
        this(path);
        this.tipoProducto = tipoProducto;
    }

    /**
     * Son las imágenes utilizadas
     *
     * @param path   La ubicación de la imagen.
     * @param moneda El tipo de moneda asociada a la imagen.
     */
    ImagenRecurso(String path, Moneda moneda) {
        this(path);
        this.moneda = moneda;
    }

    /**
     * Devuelve la imagen asociada al tipo.
     *
     * @param tipo El tipo de producto.
     * @return La imagen asociada al tipo.
     */
    public static ImagenRecurso getImagenProducto(TipoProductos tipo) {
        if (tipo == null) return ImagenRecurso.ERROR;
        return Arrays.stream(ImagenRecurso.values())
                .filter(imagenRecurso -> imagenRecurso.tipoProducto == tipo)
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve la imagen asociada a la moneda.
     *
     * @param moneda La moneda.
     * @return La imagen asociada a la moneda.
     */
    public static ImagenRecurso getImagenMoneda(Moneda moneda) {
        if (moneda == null) return ImagenRecurso.ERROR;
        return Arrays.stream(ImagenRecurso.values())
                .filter(imagenRecurso ->
                        imagenRecurso.moneda != null && imagenRecurso.moneda.getValor() == moneda.getValor()
                )
                .findFirst()
                .orElse(null);
    }

    /**
     * Es el ícono de la imagen.
     *
     * @return El ícono de la imagen.
     */
    public ImageIcon getIcon() {
        return this.icon;
    }

    /**
     * Da la imagen.
     *
     * @return La imagen.
     */
    public Image getImagen() {
        return this.imagen;
    }

    /**
     * Altura de la imagen.
     *
     * @return La altura de la imagen.
     */
    public int getHeight() {
        return this.imagen.getHeight(null);
    }

    /**
     * Ancho de la imagen.
     *
     * @return el ancho de la imagen.
     */
    public int getWidth() {
        return this.imagen.getWidth(null);
    }

    /**
     * Da una nueva imagen con tamaño diferente.
     *
     * @param width  Ancho de la imagen.
     * @param height Altura de la imagen.
     * @return La nueva imagen con sus dimensiones cambiadas.
     */
    public Image getResized(int width, int height) {
        return this.imagen.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
}
