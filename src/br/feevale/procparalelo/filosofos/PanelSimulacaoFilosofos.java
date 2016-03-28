package br.feevale.procparalelo.filosofos;

import br.feevale.procparalelo.AutoUpdatePanel;
import br.feevale.procparalelo.PanelSimulacao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Arc2D;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Painel de simulação do problema dos filósofos
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoFilosofos extends PanelSimulacao<SimulacaoFilosofos> {

    /** Panel de parâmetros */
    private PanelParametros parametros;
    
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
        if (parametros == null) {
            parametros = new PanelParametros();
        }
        return parametros;
    }

    @Override
    public JComponent getPainelEstatisticas() {
        return new JComponent() {};
    }
    
    /**
     * Painél de visualização da simulação
     */
    private class PanelVisualizacao extends AutoUpdatePanel {

        /** Cores a utilizar pelos filósofos */
        private final Color[] filosofoColors = {
            Color.yellow,
            Color.blue,
            Color.green,
        };
        
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
            if (filosofo.isVivo()) {
                g2d.setColor(new Color(0xCCFFFFFF, true));
                int newSize = (int) (size * 0.8);
                x += (size - newSize) / 2;
                y += (size - newSize) / 2;
                g2d.fill(new Arc2D.Double(x, y, newSize, newSize, 90, 3.60 * filosofo.getStarvation(), Arc2D.PIE));
            } else {
                g2d.setColor(Color.red);
                g2d.setStroke(new BasicStroke(size / 10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int desloc = (int) (size * 0.75);
                g2d.drawLine(x + desloc, y + desloc, x + size - desloc, y + size - desloc);
                g2d.drawLine(x + desloc, y + size - desloc, x + size - desloc, y + desloc);
                g2d.setStroke(new BasicStroke(1));
            }
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
    
    /**
     * Panel de parâmetros
     */
    private class PanelParametros extends JPanel {

        /** Quantidade de filósofos */
        private JTextField fFilosofos;
        /** Taxa de redução do starvation */
        private JTextField fTaxaReducaoStarvation;
        /** Taxa de restauração do starvation */
        private JTextField fRestauracaoStarvation;
        
        /**
         * Cria um novo panel de parâmetros
         */
        public PanelParametros() {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel internal = new JPanel(new GridLayout(0, 2, 5, 5));
            internal.add(new JLabel("Filosofos", JLabel.RIGHT));
            internal.add(buildFieldFilosofos());
            internal.add(new JLabel("Redução do starvation", JLabel.RIGHT));
            internal.add(buildFieldTaxaReducaoStarvation());
            internal.add(new JLabel("Restauração do starvation", JLabel.RIGHT));
            internal.add(buildFieldTaxaRestauracaoStarvation());
            internal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(internal);
            add(Box.createVerticalGlue());
        }
        
        /**
         * Cria o campo de filósofos
         * 
         * @return JComponent
         */
        private JComponent buildFieldFilosofos() {
            fFilosofos = new JTextField(String.valueOf(getSimulacao().getQuantidadeFilosofos()));
            fFilosofos.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setQuantidadeFilosofos(Integer.parseInt(fFilosofos.getText()));
                    } catch(Exception ex) {
                        fFilosofos.setText(String.valueOf(getSimulacao().getQuantidadeFilosofos()));
                    }
                }
                
            });
            return fFilosofos;
        }
        
        /**
         * Cria o campo de taxa de redução do starvation
         * 
         * @return JComponent
         */
        private JComponent buildFieldTaxaReducaoStarvation() {
            fTaxaReducaoStarvation = new JTextField(String.valueOf(getSimulacao().getTaxaReducaoStarvation()));
            fTaxaReducaoStarvation.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setTaxaReducaoStarvation(Double.parseDouble(fTaxaReducaoStarvation.getText()));
                    } catch(Exception ex) {
                        fTaxaReducaoStarvation.setText(String.valueOf(getSimulacao().getTaxaReducaoStarvation()));
                    }
                }
                
            });
            return fTaxaReducaoStarvation;
        }
        
        /**
         * Cria o campo de taxa de restauração do starvation
         * 
         * @return JComponent
         */
        private JComponent buildFieldTaxaRestauracaoStarvation() {
            fRestauracaoStarvation = new JTextField(String.valueOf(getSimulacao().getRestauracaoStarvation()));
            fRestauracaoStarvation.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setRestauracaoStarvation(Double.parseDouble(fRestauracaoStarvation.getText()));
                    } catch(Exception ex) {
                        fRestauracaoStarvation.setText(String.valueOf(getSimulacao().getRestauracaoStarvation()));
                    }
                }
                
            });
            return fRestauracaoStarvation;
        }

        @Override
        public void setEnabled(boolean enabled) {
            super.setEnabled(enabled);
            fFilosofos.setEnabled(enabled);
            fTaxaReducaoStarvation.setEnabled(enabled);
            fRestauracaoStarvation.setEnabled(enabled);
        }
        
    }
    
}
