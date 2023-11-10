package gui;

import backend.Deposito;
import backend.Expendedor;
import backend.Producto;
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
    private final ArrayList<ImagenConLabel> imagenesProductos;
    private ImagenConLabel imagenProductoComprado;
    private boolean bordesCalculados;
    private boolean addedProductos;
    private boolean productosActualizados;

    public PanelVentanaProductos(PanelExpendedor panelExpendedor) {
        this.panelExpendedor = panelExpendedor;
        this.expendedorProductos = panelExpendedor.getExpendedorProductos();
        this.sizeDeposito = new Dimension();
        this.sizeSoporte = new Dimension();
        this.preciosProductos = new ArrayList<>();
        this.imagenesProductos = new ArrayList<>();
        this.bordesCalculados = false;
        this.addedProductos = false;
        this.productosActualizados = true;
        this.imagenProductoComprado = null;

        this.setLayout(null);
        this.setBackground(Util.color("#ffffff"));
        this.setBounds(panelExpendedor.getBounds());

        for (TipoProductos tipoProducto : TipoProductos.values()) {
            final String precio = " $" + tipoProducto.getPrecio() + " ";
            final PanelTexto panelPrecio = new PanelTexto(precio, "#ffffff", "#333333");
            this.preciosProductos.add(panelPrecio);
            this.add(panelPrecio);
        }
    }

    public void actualizarProductos() {
        this.productosActualizados = false;
    }

    public ImagenConLabel getImagenProductoComprado(TipoProductos tipo, int serie) {
        final ImagenRecurso imagenRecursoProducto = ImagenRecurso.getImagenProducto(tipo);
        if (this.imagenProductoComprado != null
                && this.imagenProductoComprado.getImagenRecurso() == imagenRecursoProducto
                && this.imagenProductoComprado.getTexto().equals(Integer.toString(serie))
        ) {
            return this.imagenProductoComprado;
        }

        for (ImagenConLabel imagenProducto : this.imagenesProductos) {
            if (imagenProducto.getImagenRecurso() == imagenRecursoProducto
                    && imagenProducto.getTexto().equals(Integer.toString(serie))
            ) {
                this.imagenProductoComprado = imagenProducto;
                break;
            }
        }

        return this.imagenProductoComprado;
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
        final Font fuente = Fuentes.PIXELOID_SANS.getFuente(sizeFuente);

        int y = 0;
        for (PanelTexto panelPrecio : this.preciosProductos) {
            panelPrecio.setFont(fuente);

            final Dimension panelPrecioSize = panelPrecio.getPreferredSize();
            panelPrecio.setSize(panelPrecioSize);

            final int precioX = (this.sizeDeposito.width - panelPrecioSize.width) / 2;
            final int precioY = y + productoHeight - panelPrecioSize.height + 1;
            panelPrecio.setLocation(precioX, precioY);

            y += this.sizeDeposito.height;
        }

        this.bordesCalculados = true;
    }

    private void addProductos() {
        if (this.addedProductos && this.productosActualizados) return;

        if (!this.productosActualizados) {
            final int cantidad = this.imagenesProductos.size();
            for (int i = cantidad - 1; i >= 0; i--) {
                final ImagenConLabel imagenProducto = this.imagenesProductos.get(i);
                this.remove(imagenProducto);
                this.imagenesProductos.remove(imagenProducto);
            }
        }

        int y = 0;
        for (TipoProductos tipo : TipoProductos.values()) {
            final Deposito<Producto> productos = this.expendedorProductos.getDepositoProducto(tipo);
            final int cantidad = productos.size();
            final ImagenRecurso imagenProducto = ImagenRecurso.getImagenProducto(tipo);

            // Productos del dispensador
            final int productoHeight = (int) (this.sizeDeposito.height * 0.8);
            final float imagenScaling = (float) (productoHeight) / imagenProducto.getHeight();
            final int productoWidth = (int) (imagenProducto.getWidth() * imagenScaling);
            final Rectangle bordesImagen = new Rectangle(0, y, productoWidth, productoHeight);
            final int productoOffset = (int) (productoWidth * 0.75);

            final Font serieFuente = Fuentes.PIXELOID_SANS.getFuente(productoHeight * 0.15f);

            int cantidadACrear = 0;
            for (int i = 0; i < cantidad; i++) {
                if (bordesImagen.x > this.sizeDeposito.width) break;
                bordesImagen.x += productoOffset;
                cantidadACrear++;
            }

            for (int i = 0; i < cantidadACrear; i++) {
                bordesImagen.x -= productoOffset;
                final String serie = Integer.toString(productos.leer(cantidadACrear - i - 1).getSerie());

                final ImagenConLabel imagenProductoConSerie = new ImagenConLabel(imagenProducto);
                imagenProductoConSerie.setBounds(bordesImagen);

                boolean intersectaConPrecio = false;
                for (PanelTexto panelPrecio : this.preciosProductos) {
                    if (panelPrecio.getBounds().intersects(bordesImagen)) {
                        intersectaConPrecio = true;
                        break;
                    }
                }

                if (!intersectaConPrecio) {
                    imagenProductoConSerie.setColoresTexto("#ffffff", "#333333");
                    imagenProductoConSerie.setTextoFuente(serieFuente);
                    imagenProductoConSerie.setTexto(serie);
                }

                this.imagenesProductos.add(imagenProductoConSerie);
                this.add(imagenProductoConSerie);
            }

            y += this.sizeDeposito.height;
        }

        this.addedProductos = true;
        this.productosActualizados = true;
    }

    @Override
    public void paint(Graphics graphics) {
        this.calcularBordes();
        this.addProductos();
        super.paint(graphics);

        int y = 0;
        for (int i = 0; i < TipoProductos.values().length; i++) {
            final int productoHeight = (int) (this.sizeDeposito.height * 0.8);

            // Soporte dispensador
            final Rectangle bordesSoporte = new Rectangle(this.sizeSoporte);
            bordesSoporte.y = y + productoHeight;
            Util.fillRect(graphics, bordesSoporte, Util.color("#333333"));

            y += this.sizeDeposito.height;
        }

        this.preciosProductos.forEach(PanelTexto::repaint);
    }
}
