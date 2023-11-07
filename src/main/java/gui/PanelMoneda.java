package gui;

import backend.Moneda;

import java.awt.*;

public class PanelMoneda {
    private final Class<? extends Moneda> claseMoneda;
    private Moneda moneda;
    private final Image imagen;
    private final Rectangle bordes;

    public PanelMoneda(Class<? extends Moneda> claseMoneda, ImagenRecurso imagenRecurso) {
        this.claseMoneda = claseMoneda;
        this.imagen = imagenRecurso.getImagen();
        this.bordes = new Rectangle();
        this.renovarMoneda();
    }

    public Moneda getMoneda() {
        return this.moneda;
    }

    public void renovarMoneda() {
        try {
            this.moneda = this.claseMoneda.getDeclaredConstructor().newInstance();
        } catch (Exception ignored) {
        }
    }

    public Image getImagen() {
        return this.imagen;
    }

    public void setBordes(int x, int y, int width, int height) {
        this.bordes.x = x;
        this.bordes.y = y;
        this.bordes.width = width;
        this.bordes.height = height;
    }

    public Rectangle getBordes() {
        return this.bordes;
    }
}
