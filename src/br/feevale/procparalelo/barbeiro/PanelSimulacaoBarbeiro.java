package br.feevale.procparalelo.barbeiro;

import br.feevale.procparalelo.AutoUpdatePanel;
import br.feevale.procparalelo.PanelSimulacao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel da simulação do barbeiro
 *
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoBarbeiro extends PanelSimulacao<SimulacaoBarbeiro> {

    /** Parâmetros */
    private PanelParametros parametros;

    public PanelSimulacaoBarbeiro() {
        super(new SimulacaoBarbeiro());
    }

    @Override
    public JComponent getPainelPrincipal() {
        return new PanelSimulacao();
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
            int size = 30;
            paintFilaClientes(g2d, size);
            paintBarbeiro(g2d, size);
            g2d.dispose();
        }

        /**
         * Desenha a fila de clientes
         *
         * @param g2d
         */
        private void paintFilaClientes(Graphics2D g2d, int size) {
            int margem = 5;
            int totalLargura = (getSimulacao().getTamanhoSalaEspera() * size) +
                    ((getSimulacao().getTamanhoSalaEspera() - 1) * margem);
            int xInicial = getWidth() / 2 - totalLargura / 2;
            for (int i = 0; i < getSimulacao().getTamanhoSalaEspera(); i++) {
                int x = xInicial + (i) * (size + margem);
                paintClienteFila(g2d, i, size, x);
            }
        }

        /**
         * Desenha o barbeiro
         *
         * @param g2d
         */
        public void paintBarbeiro(Graphics2D g2d, int size) {
            if (getSimulacao() == null || getSimulacao().getBarbeiro() == null) {
                return;
            }
            int x = getWidth() / 2 - size / 2;
            if (getSimulacao().getBarbeiro().isDormindo()) {
                g2d.setColor(Color.LIGHT_GRAY);
            } else {
                g2d.setColor(new Color(0xFF5533));
            }
            g2d.fillRect(x, 150, size, (int) (size * 1.5));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, 150, size, (int) (size * 1.5));
        }

        /**
         * Desenha o cliente
         */
        private void paintClienteFila(Graphics2D g2d, int i, int size, int x) {
           if (getSimulacao() == null || getSimulacao().getFilaClientes() == null) {
               return;
           }
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

    /**
     * Panel de parâmetros
     */
    private class PanelParametros extends JPanel {

        /** Tamanho da fila de espera */
        private JTextField fTamanhoSalaEspera;
        /** Taxa de entrada de novos clientes */
        private JTextField fTaxaEntradaClientes;
        /** Tempo para cortar o cabelo de um cliente */
        private JTextField fTempoCorteCabelo;

        /**
         * Cria um novo panel de parâmetros
         */
        public PanelParametros() {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JPanel internal = new JPanel(new GridLayout(0, 2, 5, 5));
            internal.add(new JLabel("Tamanho da sala de espera", JLabel.RIGHT));
            internal.add(buildFieldTamanhoSalaEspera());
            internal.add(new JLabel("Taxa de entrada de clientes", JLabel.RIGHT));
            internal.add(buildFieldTaxaEntradaClientes());
            internal.add(new JLabel("Tempo do corte (ms)", JLabel.RIGHT));
            internal.add(buildFieldTempoCorteCabelo());
            internal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(internal);
            add(Box.createVerticalGlue());
        }

        /**
         * Cria o campo de tamanho da sala de espera
         *
         * @return JComponent
         */
        private JComponent buildFieldTamanhoSalaEspera() {
            fTamanhoSalaEspera = new JTextField(String.valueOf(getSimulacao().getTamanhoSalaEspera()));
            fTamanhoSalaEspera.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setTamanhoSalaEspera(Integer.parseInt(fTamanhoSalaEspera.getText()));
                    } catch(Exception ex) {
                        fTamanhoSalaEspera.setText(String.valueOf(getSimulacao().getTamanhoSalaEspera()));
                    }
                }

            });
            return fTamanhoSalaEspera;
        }

        /**
         * Cria o campo de taxa de entrada de clientes
         *
         * @return JComponent
         */
        private JComponent buildFieldTaxaEntradaClientes() {
            fTaxaEntradaClientes = new JTextField(String.valueOf(getSimulacao().getIndiceNovosCliente()));
            fTaxaEntradaClientes.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setIndiceNovosCliente(Integer.parseInt(fTaxaEntradaClientes.getText()));
                    } catch(Exception ex) {
                        fTaxaEntradaClientes.setText(String.valueOf(getSimulacao().getIndiceNovosCliente()));
                    }
                }

            });
            return fTaxaEntradaClientes;
        }

        /**
         * Cria o campo de tempo do corte
         *
         * @return JComponent
         */
        private JComponent buildFieldTempoCorteCabelo() {
            fTempoCorteCabelo = new JTextField(String.valueOf(getSimulacao().getTempoEsperaAtendimento()));
            fTempoCorteCabelo.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        getSimulacao().setTempoEsperaAtendimento(Integer.parseInt(fTempoCorteCabelo.getText()));
                    } catch(Exception ex) {
                        fTempoCorteCabelo.setText(String.valueOf(getSimulacao().getTempoEsperaAtendimento()));
                    }
                }

            });
            return fTempoCorteCabelo;
        }

        @Override
        public void setEnabled(boolean enabled) {
            super.setEnabled(enabled);
            fTamanhoSalaEspera.setEnabled(enabled);
            fTaxaEntradaClientes.setEnabled(enabled);
            fTempoCorteCabelo.setEnabled(enabled);
        }

    }


}
