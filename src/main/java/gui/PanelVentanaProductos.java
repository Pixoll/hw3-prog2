package gui;

import backend.Expendedor;
import backend.TipoProductos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelVentanaProductos extends JPanel {
    private final PanelExpendedor panelExpendedor;
    private final Expendedor expendedorProductos;
    private final Dimension sizeDeposito;
    private final Dimension sizeSoporte;
    private final ArrayList<PanelTexto> preciosProductos;
    private boolean bordesCalculados;

    public PanelVentanaProductos(PanelExpendedor panelExpendedor) {
        this.panelExpendedor = panelExpendedor;
        this.expendedorProductos = panelExpendedor.getExpendedorProductos();
        this.sizeDeposito = new Dimension();
        this.sizeSoporte = new Dimension();
        this.preciosProductos = new ArrayList<>();
        this.bordesCalculados = false;

        this.setLayout(null);
        this.setBackground(Util.color("#ffffff"));
        this.setBounds(panelExpendedor.getBounds());

        for (TipoProductos tipoProducto : TipoProductos.values()) {
            final String precio = " $" + tipoProducto.getPrecio();
            final PanelTexto panelPrecio = new PanelTexto(precio, "#ffffff", "#333333");
            this.preciosProductos.add(panelPrecio);
            this.add(panelPrecio);
        }
    }

    private void calcularBordes() {
        if (this.bordesCalculados) return;

        final Rectangle bordes = this.panelExpendedor.getVentanaProductosBordes();
        this.setBounds(bordes);

        final int cantidadTipos = TipoProductos.values().length;
        this.sizeDeposito.width = bordes.width;
        this.sizeDeposito.height = bordes.height / cantidadTipos;

        this.sizeSoporte.width = bordes.width;
        this.sizeSoporte.height = (int) (this.sizeDeposito.height * 0.1);

        final int productoHeight = (int) (this.sizeDeposito.height * 0.8);
        final int sizeFuente = this.sizeSoporte.height * 2;
        final Font fuente = Fuentes.PIXELOID_SANS.getFuente().deriveFont((float) (sizeFuente * 0.9)); // new Font("Monospaced", Font.PLAIN, sizeFuente);

        int y = 0;
        for (PanelTexto panelPrecio : this.preciosProductos) {
            final int widthPrecio = (panelPrecio.getText().length() - 2) * sizeFuente;
            final int precioX = (this.sizeDeposito.width - widthPrecio) / 2;
            final int precioY = y + productoHeight - (this.sizeSoporte.height * 2);

            panelPrecio.setBounds(precioX, precioY, widthPrecio, sizeFuente);
            panelPrecio.setFont(fuente);

            y += this.sizeDeposito.height;
        }

        this.bordesCalculados = true;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        super.paint(graphics);

        int y = 0;
        for (TipoProductos tipo : TipoProductos.values()) {
            final int cantidad = this.expendedorProductos.getProductoCantidad(tipo);
            final Image imagenProducto = ImagenRecurso.getImagenProducto(tipo);

            // Productos del dispensador
            final float imagenScaling = (float) (this.sizeDeposito.height) / imagenProducto.getHeight(null) * 0.8f;
            final int productoWidth = (int) (imagenProducto.getWidth(null) * imagenScaling);
            final int productoHeight = (int) (this.sizeDeposito.height * 0.8);
            final Rectangle bordesImagen = new Rectangle(0, y, productoWidth, productoHeight);
            final int productoOffset = (int) (productoWidth * 0.75);

            int cantidadADibujar = 0;
            for (int i = 0; i < cantidad; i++) {
                if (bordesImagen.x > this.sizeDeposito.width) break;
                bordesImagen.x += productoOffset;
                cantidadADibujar++;
            }

            for (int i = 0; i < cantidadADibujar; i++) {
                bordesImagen.x -= productoOffset;
                Util.drawImage(graphics, imagenProducto, bordesImagen);
            }

            // Soporte dispensador
            final Rectangle bordesSoporte = new Rectangle(this.sizeSoporte);
            bordesSoporte.y = y + productoHeight;
            Util.fillRect(graphics, bordesSoporte, Util.color("#333333"));

            y += this.sizeDeposito.height;
        }

        this.preciosProductos.forEach(PanelTexto::repaint);
    }
}
