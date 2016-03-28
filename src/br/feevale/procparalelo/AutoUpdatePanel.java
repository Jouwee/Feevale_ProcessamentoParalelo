package br.feevale.procparalelo;

import javax.swing.JComponent;

/**
 * Panel que se auto-atualiza
 *
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class AutoUpdatePanel extends JComponent {

    /** Taxa de atualização da simulação */
    private static final int FRAMES_PER_SECOND = 30;

    /**
     * Cria o painel
     */
    public AutoUpdatePanel() {
        super();
        Thread updateThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000 / FRAMES_PER_SECOND);
                    repaint();
                }
            } catch (Exception e) {
            }
        });
        updateThread.setDaemon(true);
        updateThread.start();
    }

}
