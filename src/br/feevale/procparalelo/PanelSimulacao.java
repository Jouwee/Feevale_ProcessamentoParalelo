package br.feevale.procparalelo;

import javax.swing.JComponent;

/**
 * Panel genérico de simulação
 * 
 * @author  Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public interface PanelSimulacao {
 
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
    
}
