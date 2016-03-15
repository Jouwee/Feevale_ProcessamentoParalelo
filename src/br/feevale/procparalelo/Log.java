package br.feevale.procparalelo;

/**
 * Classe de log
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Log {
    
    /**
     * Grava uma mensagem no log
     * 
     * @param mensagem
     * @param substituicoes 
     */
    public void grava(String mensagem, Object... substituicoes) {
        System.out.println(String.format(mensagem, substituicoes));
    }
    
}
