
package model;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Luis Miguel
 */
public class ProgressBar extends JFrame {
    private JLabel etiqueta;
    private JProgressBar barra;
    private Timer temporizador;
    private int progreso;

    public ProgressBar() {
        super("Cargando...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setResizable(false);
        setLocationRelativeTo(null);

        etiqueta = new JLabel("Cargando...");
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        add(etiqueta, BorderLayout.NORTH);

        barra = new JProgressBar();
        barra.setMaximum(50);
        barra.setStringPainted(true);
        add(barra, BorderLayout.CENTER);
    }

    public void iniciar() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    progreso = i;
                    barra.setValue(50);
                    Thread.sleep(1);
                }
                return null;
            }

            @Override
            protected void done() {
                setVisible(false);
                dispose();
            }
        };
        worker.execute();
        setVisible(true);
    }

    

    
}
