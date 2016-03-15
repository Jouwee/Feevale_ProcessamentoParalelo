package br.feevale.procparalelo.filosofos;

import br.feevale.procparalelo.PanelSimulacao;
import javax.swing.JComponent;

/**
 * Painel de simulação do problema dos filósofos
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoFilosofos implements PanelSimulacao {

    /** Simulação dos filósofos */
    private final SimulacaoFilosofos simulacao;
    
    /**
     * Cria novo painel de simulação
     */
    public PanelSimulacaoFilosofos() {
        simulacao = new SimulacaoFilosofos();
    }
    
    @Override
    public JComponent getPainelPrincipal() {
        simulacao.executa();
        return new JComponent() {};
    }

    @Override
    public JComponent getPainelPropriedades() {
        return new JComponent() {};
    }

    @Override
    public JComponent getPainelEstatisticas() {
        return new JComponent() {};
    }
    
}
