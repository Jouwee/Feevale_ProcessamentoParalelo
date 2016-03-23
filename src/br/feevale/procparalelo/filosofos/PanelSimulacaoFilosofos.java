package br.feevale.procparalelo.filosofos;

import br.feevale.procparalelo.PanelSimulacao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.List;
import javax.swing.JComponent;

/**
 * Painel de simulação do problema dos filósofos
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoFilosofos extends PanelSimulacao<SimulacaoFilosofos> {

    /**
     * Cria novo painel de simulação
     */
    public PanelSimulacaoFilosofos() {
        super(new SimulacaoFilosofos());
    }
    
    @Override
    public JComponent getPainelPrincipal() {
        return new PanelVisualizacao();
    }

    @Override
    public JComponent getPainelPropriedades() {
        return new JComponent() {};
    }

    @Override
    public JComponent getPainelEstatisticas() {
        return new JComponent() {};
    }
    
    /**
     * Painél de visualização da simulação
     */
    private class PanelVisualizacao extends JComponent {

        /** Cores a utilizar pelos filósofos */
        private final Color[] filosofoColors = {
            Color.red,
            Color.blue,
            Color.green,
        };
        
        /**
         * Cria o painel
         */
        public PanelVisualizacao() {
            Thread updateThread = new Thread(() -> {
                try {
                    while(true) {
                        Thread.sleep(1000 / 60);
                        repaint();
                    }
                } catch (Exception e) { }
            });
            updateThread.setDaemon(true);
            updateThread.start();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintFilosofos(g2d);
            paintGarfos(g2d);
            g2d.dispose();
        }
        
        /**
         * Desenha os filósofos
         * 
         * @param g2d 
         */
        private void paintFilosofos(Graphics2D g2d) {
            List<Filosofo> filosofos = getSimulacao().getFilosofos();
            Point center = new Point(getWidth() / 2, getHeight() / 2);
            double radius = (double) Math.min(getWidth(), getHeight()) * .35d;
            int size = (int) (radius / filosofos.size() * 4);
            for (int i = 0; i < filosofos.size(); i++) {
                Filosofo filosofo = filosofos.get(i);
                double angle = Math.toRadians(360d * ((double)i / filosofos.size()) - 90);
                int x = (int) (Math.cos(angle) * radius + center.x) - size / 2;
                int y = (int) (Math.sin(angle) * radius + center.y) - size / 2;
                g2d.setColor(getColor(filosofo));
                g2d.fillOval(x, y, size, size);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x, y, size, size);
                paintBarraVida(g2d, x, y, size, filosofo);
            }
        }
        
        /**
         * Desenha a barra de vida do filósofo
         * 
         * @param g2d
         * @param x
         * @param y
         * @param size 
         */
        private void paintBarraVida(Graphics2D g2d, int x, int y, int size, Filosofo filosofo) {
            g2d.setColor(new Color(0x80FFFFFF, true));
            int newSize = (int) (size * 0.8);
            x += (size - newSize) / 2;
            y += (size - newSize) / 2;
            g2d.fill(new Arc2D.Double(x, y, newSize, newSize, 90, 3.60 * filosofo.getStarvation(), Arc2D.PIE));
        }
        
        /**
         * Desenha os garfos
         * 
         * @param g2d 
         */
        private void paintGarfos(Graphics2D g2d) {
            List<Garfo> garfos = getSimulacao().getGarfos();
            Point center = new Point(getWidth() / 2, getHeight() / 2);
            double radius = (double) Math.min(getWidth(), getHeight()) * .35d;
            int size = (int) (radius / garfos.size() * 2);
            for (int i = 0; i < garfos.size(); i++) {
                Garfo garfo = garfos.get(i);
                double angle = 360d * ((double)i / garfos.size()) - 90;
                angle += 360d * ((0.5 / garfos.size()));
                angle = Math.toRadians(angle);
                int x = (int) (Math.cos(angle) * radius + center.x);
                int y = (int) (Math.sin(angle) * radius + center.y);
                g2d.setColor(getColor(garfo.getFilosofo()));
                g2d.fillOval(x - size / 2, y - size / 2, size, size);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x - size / 2, y - size / 2, size, size);
            }
        }
        
        /**
         * Retorna a cor para um filósofo
         * 
         * @param filosofo
         * @return Color
         */
        private Color getColor(Filosofo filosofo) {
            if (filosofo == null) {
                return Color.LIGHT_GRAY;
            }
            return filosofoColors[filosofo.getIndice() % filosofoColors.length];
        }
        
    }
    
}
