package br.feevale.procparalelo;

/**
 * Simulação abstrata
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public abstract class AbstractSimulacao {
    
    /** Log da simulação */
    private final Log log;

    /**
     * Cria uma nova simulação
     */
    public AbstractSimulacao() {
        log = new Log();
    }
    
    /**
     * Executa a simulação
     */
    public void executa() {
        inicializa();
        iniciaSimulacao();
    }
    
    /**
     * Inicializa execução da simulação
     */
    public abstract void inicializa();

    /**
     * Inicia a simulação
     */
    public abstract void iniciaSimulacao();
    
    /**
     * Encerra a simulação
     */
    public abstract void encerra();

    /**
     * Retorna se a simulação está rodando
     * 
     * @return boolean
     */
    public abstract boolean isRunning();
    
    /**
     * Retorna o log dessa simulação
     * 
     * @return Log
     */
    public Log getLog() {
        return log;
    }
    
}
