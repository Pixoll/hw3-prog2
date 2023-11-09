package gui;

import backend.*;

import java.awt.*;
import java.util.ArrayList;

public class PanelPopupInsertarMoneda extends PanelPopup {
    private final ArrayList<PanelMoneda> panelesMoneda;
    private final ArrayList<BotonInsertarMoneda> botonesMonedas;
    private final Image imagenFlecha;
    private final Rectangle bordesFlecha;
    private boolean botonesAdded;

    public PanelPopupInsertarMoneda(PanelComprador panelComprador) {
        super(panelComprador);

        this.panelesMoneda = new ArrayList<>();
        this.botonesMonedas = new ArrayList<>();
        this.imagenFlecha = ImagenRecurso.FLECHA_ARRIBA.getImagen();
        this.bordesFlecha = new Rectangle();
        this.botonesAdded = false;

        this.panelesMoneda.add(new PanelMoneda(Moneda100.class));
        this.panelesMoneda.add(new PanelMoneda(Moneda500.class));
        this.panelesMoneda.add(new PanelMoneda(Moneda1000.class));
        this.panelesMoneda.add(new PanelMoneda(Moneda1500.class));

        this.setLayout(null);
        this.setBackground(Util.color("#cde5b3"));
        this.setBounds(panelComprador.getBounds());
    }

    public ArrayList<PanelMoneda> getPanelesMoneda() {
        return this.panelesMoneda;
    }

    public PanelMoneda getPanelMonedaSeleccionado() {
        PanelMoneda panelSeleccionado = null;
        for (PanelMoneda panelMoneda : this.panelesMoneda) {
            if (panelMoneda.isSeleccionado()) {
                panelSeleccionado = panelMoneda;
                break;
            }
        }
        return panelSeleccionado;
    }

    public void renovarMonedas() {
        for (PanelMoneda panelMoneda : this.panelesMoneda) {
            panelMoneda.renovarMoneda();
        }
    }

    @Override
    protected void calcularBordes() {
        if (this.bordesCalculados) return;

        final Rectangle compradorBordes = this.panelComprador.getBordes();

        this.bordes.y = compradorBordes.y * 2;
        this.bordes.x = compradorBordes.x + this.bordes.y;
        this.bordes.width = compradorBordes.width - this.bordes.y;
        final int slotSize = this.bordes.width / 4;
        this.bordes.height = (int) (slotSize * 0.9);

        final int monedaSize = (int) (slotSize * 0.9);
        final int monedaOffset = slotSize - monedaSize;
        int i = 0;

        for (PanelMoneda panelMoneda : this.panelesMoneda) {
            final int monedaX = i * (monedaSize + monedaOffset);
            panelMoneda.setBordes(monedaX, 0, monedaSize, monedaSize);
            i++;
        }

        final int flechaSize = monedaSize / 2;
        final float flechaRatio = (float) (this.imagenFlecha.getWidth(null)) / this.imagenFlecha.getHeight(null);
        this.bordesFlecha.y = (int) (monedaSize * 1.2);
        this.bordesFlecha.height = flechaSize;
        this.bordesFlecha.width = (int) (this.bordesFlecha.height * flechaRatio);
        this.bordesFlecha.x = (monedaSize - this.bordesFlecha.width) / 2;

        this.bordes.height = this.bordesFlecha.y + this.bordesFlecha.height;
        this.setBounds(this.bordes);

        this.bordesCalculados = true;
    }

    private void addBotones() {
        if (this.botonesAdded) return;

        for (PanelMoneda panelMoneda : this.panelesMoneda) {
            this.botonesMonedas.add(new BotonInsertarMoneda(panelMoneda, this));
        }

        for (BotonInsertarMoneda boton : this.botonesMonedas) {
            final Rectangle bordes = boton.getPanelMoneda().getBordes();
            boton.setBounds(bordes);
            this.add(boton);
        }

        this.botonesAdded = true;
    }

    private void toggleBotonesMonedas(boolean estado) {
        for (BotonInsertarMoneda boton : this.botonesMonedas) {
            boton.setEnabled(estado);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        this.addBotones();
        super.paint(graphics);

        if (this.isAbierto()) {
            for (PanelMoneda panelMoneda : this.panelesMoneda) {
                final Rectangle bordesMoneda = panelMoneda.getBordes();
                Util.drawImage(graphics, panelMoneda.getImagen(), bordesMoneda, this);

                if (panelMoneda.isSeleccionado()) {
                    final Rectangle bordesFlecha = new Rectangle(this.bordesFlecha);
                    bordesFlecha.x += bordesMoneda.x;
                    Util.drawImage(graphics, this.imagenFlecha, bordesFlecha);
                }
            }

            this.toggleBotonesMonedas(true);
            this.popupLimpiado = false;
            return;
        }

        if (!this.popupLimpiado) {
            this.popupLimpiado = true;
            this.toggleBotonesMonedas(false);
            this.panelComprador.repaint();
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
        if (this.popupLimpiado) return true;
        return super.imageUpdate(img, infoflags, x, y, w, h);
    }
}
