package gui;

import backend.Moneda100;
import backend.Moneda1000;
import backend.Moneda1500;
import backend.Moneda500;

import java.awt.*;
import java.util.ArrayList;

public class PanelPopupInsertarMoneda extends PanelPopup {
    private final ArrayList<PanelMoneda> panelesMoneda;

    public PanelPopupInsertarMoneda(PanelComprador panelComprador) {
        super(panelComprador);

        this.panelesMoneda = new ArrayList<>();
        this.panelesMoneda.add(new PanelMoneda(Moneda100.class, ImagenRecurso.MONEDA100));
        this.panelesMoneda.add(new PanelMoneda(Moneda500.class, ImagenRecurso.MONEDA500));
        this.panelesMoneda.add(new PanelMoneda(Moneda1000.class, ImagenRecurso.MONEDA1000));
        this.panelesMoneda.add(new PanelMoneda(Moneda1500.class, ImagenRecurso.MONEDA1500));

        this.setLayout(null);
        this.setBackground(Util.color("#cde5b3"));
        this.setBounds(panelComprador.getBounds());
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

        this.setBounds(this.bordes);

        final int monedaSize = (int) (slotSize * 0.9);
        final int monedaOffset = slotSize - monedaSize;
        int i = 0;

        for (PanelMoneda panelMoneda : this.panelesMoneda) {
            final int monedaX = i * (monedaSize + monedaOffset);
            panelMoneda.setBordes(monedaX, 0, monedaSize, monedaSize);
            i++;
        }

        this.bordesCalculados = true;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        if (this.isAbierto()) {
            for (PanelMoneda panelMoneda : this.panelesMoneda) {
                final Rectangle monedaBordes = panelMoneda.getBordes();
                graphics.drawImage(
                        panelMoneda.getImagen(),
                        monedaBordes.x, monedaBordes.y, monedaBordes.width, monedaBordes.height,
                        this
                );
            }
            this.popupLimpiado = false;
            return;
        }

        if (!this.popupLimpiado) {
            this.popupLimpiado = true;
            this.panelComprador.repaint();
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
        if (this.popupLimpiado) return true;
        return super.imageUpdate(img, infoflags, x, y, w, h);
    }
}
