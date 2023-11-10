package gui;

/**
 * Es el botón que activa el popup del Numpad del expendedor.
 */
public class PanelExpendedorNumpad extends PanelExpendedorSeleccionable {
    /**
     * Es el botón que activa el popup del Numpad del expendedor.
     * @param panelExpendedor Panel Máquina expendedora que se encuentra a la derecha de la GUI.
     */
    public PanelExpendedorNumpad(PanelExpendedor panelExpendedor) {
        super(
                panelExpendedor,
                PanelExpendedor::getNumpadBordes,
                ImagenRecurso.NUMPAD,
                ImagenRecurso.NUMPAD_BORDE,
                ImagenRecurso.NUMPAD_SELECCIONADO,
                ImagenRecurso.NUMPAD_SELECCIONADO_BORDE
        );
    }
}
