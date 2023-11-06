package gui;

public class PanelExpendedorInsertarMoneda extends PanelExpendedorSeleccionable {
    public PanelExpendedorInsertarMoneda(PanelExpendedor panelExpendedor) {
        super(
                panelExpendedor,
                PanelExpendedor::getInsertarMonedaBordes,
                ImagenRecurso.INSERTAR_MONEDA,
                ImagenRecurso.INSERTAR_MONEDA_BORDE,
                ImagenRecurso.INSERTAR_MONEDA_SELECCIONADO,
                ImagenRecurso.INSERTAR_MONEDA_SELECCIONADO_BORDE
        );
    }
}
