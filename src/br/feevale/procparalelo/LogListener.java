package br.feevale.procparalelo;

/**
 * Listener de logs
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public interface LogListener {
    
    /**
     * Entrada adicionada no log
     * 
     * @param entry 
     */
    public void entryAdded(String entry);
    
}
