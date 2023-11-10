package gui;

/**
 * Es el botón para abrir el popup donde se inserta la moneda.
 */
public class PanelExpendedorInsertarMoneda extends PanelExpendedorSeleccionable {
    /**
     * Es el botón para abrir el popup donde se inserta la moneda.
     * @param panelExpendedor Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     */
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
