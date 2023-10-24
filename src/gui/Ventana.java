package gui;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana() {
        super();

        this.setTitle("Expendedor Mágico");
        this.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(Util.getRecursoPath(Ventana.class, "/images/icon.jpg"));
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Cuando no está maximizado
        this.setSize(1080, 720);

        final PanelPrincipal panel = new PanelPrincipal(this.getWidth(), this.getHeight());
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
