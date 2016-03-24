package br.feevale.procparalelo;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe de log
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Log {
    
    /** Listeners do log */
    private final List<LogListener> listeners;
    /** Linhas do log */
    private final List<String> lines;

    /**
     * Cria novo log
     */
    public Log() {
        this.listeners = new LinkedList<>();
        this.lines = new LinkedList<>();
    }
    
    /**
     * Grava uma mensagem no log
     * 
     * @param mensagem
     * @param substituicoes 
     */
    public synchronized void grava(String mensagem, Object... substituicoes) {
        String formatted = String.format(mensagem, substituicoes);
        for (LogListener listener : listeners) {
            listener.entryAdded(formatted);
        }
        lines.add(formatted);
    }

    /**
     * Retorna as linhas do log
     * 
     * @return {@code List<String>}
     */
    public List<String> getLines() {
        return new LinkedList<>(lines);
    }
    
    /**
     * Adiciona um listener
     * 
     * @param listener 
     */
    public void addListener(LogListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Remove um listener
     * 
     * @param listener 
     */
    public void removeListener(LogListener listener) {
        listeners.remove(listener);
    }
    
}
