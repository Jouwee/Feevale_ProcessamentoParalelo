package br.feevale.procparalelo.filosofos;

/**
 * Agente do filósofo
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public abstract class Filosofo {
    
    /**
     * Inicia a execução do agente
     */
    public void inicia() {
        Thread threadAgente = new Thread(() -> {
            try {
                while(true) {
                    loop();
                }
            } catch(InterruptedException e) { }
        });
        threadAgente.setDaemon(true);
        threadAgente.start();
    }
    
    /**
     * Método que ficará sento chamado em Loop
     * 
     * @throws InterruptedException
     */
    public abstract void loop() throws InterruptedException;
    
}
