package br.feevale.procparalelo.filosofos;

/**
 * Agente do filósofo
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Filosofo {
    
    /** Simulação que a o filósofo pertence */
    private final SimulacaoFilosofos simulacao;
    /** Índice deste filósofo */
    private int indice;
    /** Controle de starvation */
    private double starvation;
    /** Indica se o filosofo esta comendo */
    private boolean comendo;
    /** Indica se o filósofo está vivo */
    private boolean vivo;
    /** Thread do agente */
    private Thread threadAgente;
    /** Thread de redução do starvation */
    private Thread threadReducaoStarvation;

    /**
     * Cria um novo filósofo
     * 
     * @param simulacao 
     */
    public Filosofo(SimulacaoFilosofos simulacao) {
        this.simulacao = simulacao;
    }
    
    /**
     * Inicia a execução do agente
     */
    public void inicia() {
        starvation = 100;
        vivo = true;
        threadAgente = new Thread(() -> {
            try {
                while(true) {
                    if (!vivo) {
                        return;
                    }
                    try {
                        loop();
                    } catch (GarfoEmUsoException | IllegalArgumentException ex) {
                        ex.printStackTrace();
                    }
                    log("Fim da iteração. Starvation: %1$f", starvation);
                }
            } catch(InterruptedException e) { }
        });
        threadAgente.setDaemon(true);
        threadAgente.start();
        threadReducaoStarvation = new Thread(() -> {
            try {
                while(true) {
                    if (!vivo) {
                        return;
                    }
                    Thread.sleep(100);
                    if (comendo) {
                        addStarvation(simulacao.getRestauracaoStarvation());
                    } else {
                        addStarvation(-simulacao.getTaxaReducaoStarvation());
                    }
                }
            } catch(InterruptedException e) { }
        });
        threadReducaoStarvation.setDaemon(true);
        threadReducaoStarvation.start();
    }
    
    /**
     * Método que ficará sento chamado em Loop
     * 
     * @throws InterruptedException
     * @throws br.feevale.procparalelo.filosofos.GarfoEmUsoException
     */
    public void loop() throws InterruptedException, GarfoEmUsoException {
        if (!isGarfoDireitoEmUso()) {
            try {
                pegaGarfoDireito();
                if (!isGarfoEsquerdoEmUso()) {
                    try {
                        pegaGarfoEsquerdo();
                        comer(1);
                    } catch (GarfoEmUsoException e) {}
                    largaGarfoEsquerdo();
                }
            } catch (GarfoEmUsoException e) {}
            largaGarfoDireito();
        }
        espera(getStarvation() / 100);
    }
    
    /**
     * Aguarda N segundos
     * 
     * @param segundos 
     * @throws java.lang.InterruptedException 
     */
    public void espera(double segundos) throws InterruptedException {
        log("Aguardando %1$s segundos", segundos);
        Thread.sleep((long) (Math.max(segundos, 0) * 1000));
    }
    
    /**
     * Retorna o garfo da esquerda
     * 
     * @return Garfo
     */
    public Garfo getGarfoEsquerda() {
        return simulacao.getGarfo(indice - 1);
    }
    
    /**
     * Retorna o garfo da direita
     * 
     * @return Garfo
     */
    public Garfo getGarfoDireita() {
        return simulacao.getGarfo(indice);
    }
    
    /**
     * Retorna se o garfo à esquerda está em uso
     * 
     * @return boolean
     */
    public boolean isGarfoEsquerdoEmUso() {
        Filosofo filosofo = getGarfoEsquerda().getFilosofo();
        boolean isLivre = filosofo != null && filosofo != this;
        log("Verificou se o garfo à esquerda estava em uso (%1$b)", isLivre);
        return isLivre;
    }
    
    /**
     * Retorna se o garfo à direita está em uso
     * 
     * @return boolean
     */
    public boolean isGarfoDireitoEmUso() {
        Filosofo filosofo = getGarfoDireita().getFilosofo();
        boolean isLivre = filosofo != null && filosofo != this;
        log("Verificou se o garfo à direita estava em uso (%1$b)", isLivre);
        return isLivre;
    }
    
    /**
     * Pega o garfo à direita
     * 
     * @throws br.feevale.procparalelo.filosofos.GarfoEmUsoException
     */
    public void pegaGarfoDireito() throws GarfoEmUsoException {
        log("Peguei o garfo à direita");
        getGarfoDireita().setFilosofo(this);
    }
    
    /**
     * Pega o garfo à esquerda
     * 
     * @throws br.feevale.procparalelo.filosofos.GarfoEmUsoException
     */
    public void pegaGarfoEsquerdo() throws GarfoEmUsoException {
        log("Peguei o garfo à esquerda");
        getGarfoEsquerda().setFilosofo(this);
    }
    
    /**
     * Larga o garfo direito
     */
    public void largaGarfoDireito() {
        log("Larguei o garfo à direita");
        getGarfoDireita().clearFilosofo(this);
    }
    
    /**
     * Larga o garfo direito
     */
    public void largaGarfoEsquerdo() {
        log("Larguei o garfo à esquerda");
        getGarfoEsquerda().clearFilosofo(this);
    }
    
    /**
     * Preenche o controle de starvation
     * 
     * @param segundos 
     * @throws InterruptedException
     */
    public void comer(double segundos) throws InterruptedException {
        log("Comendo por %2$f segundos (Starvation %1$f)", starvation, segundos);
        comendo = true;
        espera(segundos);
        comendo = false;
        log("Terminei de comer (Starvation %1$f)", starvation);
    }
    
    /**
     * Loga uma mensagem
     * 
     * @param mensagem 
     * @param substituicoes
     */
    public void log(String mensagem, Object... substituicoes) {
        simulacao.getLog().grava("Filosofo_" + indice + " " + String.format(mensagem, substituicoes)); 
    }

    /**
     * Retorna o índice do filósofo
     * 
     * @return int
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Define o índice do filósofo
     * 
     * @param indice 
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Adiciona ao starvation
     * 
     * @param value 
     */
    public synchronized void addStarvation(double value) {
        starvation += value;
        if (starvation > 100) {
            starvation = 100;
        }
        if (starvation < 0) {
            vivo = false;
            interrupt();
            log("Eu morri de fome x(");
        }
    }
    
    /**
     * Retorna o Starvation
     * 
     * @return double
     */
    public double getStarvation() {
        return starvation;
    }
    
    /**
     * Interrompe as threads
     */
    public void interrupt() {
        threadAgente.interrupt();
        threadReducaoStarvation.interrupt();
    }

    /**
     * Retorna a thread agente
     * 
     * @return Thread
     */
    public Thread getThreadAgente() {
        return threadAgente;
    }

    /**
     * Retorna a thread de redução do starvation
     * 
     * @return Thread
     */
    public Thread getThreadReducaoStarvation() {
        return threadReducaoStarvation;
    }

    /**
     * Retorna se o filósofo está vivo
     * 
     * @return boolean
     */
    public boolean isVivo() {
        return vivo;
    }

}
