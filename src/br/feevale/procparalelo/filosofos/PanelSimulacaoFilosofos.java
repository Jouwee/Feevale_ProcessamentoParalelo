package br.feevale.procparalelo.filosofos;

import br.feevale.procparalelo.PanelSimulacao;
import javax.swing.JComponent;

/**
 * Painel de simulação do problema dos filósofos
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelSimulacaoFilosofos implements PanelSimulacao {

    @Override
    public JComponent getPainelPrincipal() {
        return new JComponent() {};
    }

    @Override
    public JComponent getPainelPropriedades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComponent getPainelEstatisticas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
