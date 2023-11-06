package gui;

public class PanelExpendedorNumpad extends PanelExpendedorSeleccionable {
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
