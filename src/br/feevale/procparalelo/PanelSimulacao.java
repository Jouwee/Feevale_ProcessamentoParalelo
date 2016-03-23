package br.feevale.procparalelo;

import javax.swing.JComponent;

/**
 * Panel genérico de simulação
 * 
 * @author  Cristian Dias, Gustavo Cassel e Nícolas Pohren
 * @param <T>
 */
public abstract class PanelSimulacao<T extends AbstractSimulacao> {

    /** Simulação dos filósofos */
    private final T simulacao;

    /**
     * Cria o panel de simulação
     * 
     * @param simulacao 
     */
    public PanelSimulacao(T simulacao) {
        this.simulacao = simulacao;
    }
    
    /**
     * Retorna o painel principal
     * 
     * @return JComponent
     */
    public abstract JComponent getPainelPrincipal();
    
    /**
     * Retorna o painel de propriedades da simulação
     * 
     * @return JComponent
     */
    public abstract JComponent getPainelPropriedades();
    
    /**
     * Retorna um painel com as estatísticas da simulação
     * 
     * @return JComponent
     */
    public abstract JComponent getPainelEstatisticas();

    /**
     * Retorna a simulação
     * 
     * @return T
     */
    public T getSimulacao() {
        return simulacao;
    }
    
}
