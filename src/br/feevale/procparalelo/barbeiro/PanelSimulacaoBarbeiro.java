package br.feevale.procparalelo.barbeiro;

import br.feevale.procparalelo.AutoUpdatePanel;
import br.feevale.procparalelo.PanelSimulacao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Panel da simulação do barbeiro
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoBarbeiro extends PanelSimulacao<SimulacaoBarbeiro> {

    public PanelSimulacaoBarbeiro() {
        super(new SimulacaoBarbeiro());
    }
    
    @Override
    public JComponent getPainelPrincipal() {
        return new PanelSimulacao();
    }

    @Override
    public JComponent getPainelPropriedades() {
        return new JPanel();
    }

    @Override
    public JComponent getPainelEstatisticas() {
        return new JPanel();
    }
    
    /**
     * Panel da simulação
     */
    private class PanelSimulacao extends AutoUpdatePanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintFilaClientes(g2d);
            g2d.dispose();
        }

        /**
         * Desenha a fila de clientes
         * 
         * @param g2d 
         */
        private void paintFilaClientes(Graphics2D g2d) {
            int size = 30;
            for (int i = 0; i < getSimulacao().getTamanhoSalaEspera(); i++) {
                int x = (i + 1) * 50;
                paintClienteFila(g2d, i, size, x);
            }
        }
        
        /**
         * Desenha o cliente
         */
        private void paintClienteFila(Graphics2D g2d, int i, int size, int x) {
           if (i < getSimulacao().getFilaClientes().size()) {
               g2d.setColor(new Color(0x3355FF));
           } else {
               g2d.setColor(Color.LIGHT_GRAY);
           }
           g2d.fillRect(x, 50, size, (int) (size * 1.5));
           g2d.setColor(Color.BLACK);
           g2d.drawRect(x, 50, size, (int) (size * 1.5));
        }
        
    }
    
}
