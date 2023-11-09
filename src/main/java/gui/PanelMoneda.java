package gui;

import backend.Moneda;

import java.awt.*;

public class PanelMoneda {
    private final Class<? extends Moneda> claseMoneda;
    private final ImagenRecurso imagenRecurso;
    private final Rectangle bordes;
    private Moneda moneda;
    private boolean seleccionado;

    public PanelMoneda(Class<? extends Moneda> claseMoneda) {
        this.claseMoneda = claseMoneda;
        this.bordes = new Rectangle();
        this.renovarMoneda();
        this.imagenRecurso = ImagenRecurso.getImagenMoneda(this.moneda);
        this.seleccionado = false;
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
        return this.imagenRecurso.getImagen();
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

    public boolean isSeleccionado() {
        return this.seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
