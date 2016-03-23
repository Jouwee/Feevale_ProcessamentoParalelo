package br.feevale.procparalelo.filosofos;

import br.feevale.procparalelo.AbstractSimulacao;
import java.util.LinkedList;
import java.util.List;

/**
 * Simulação do problema dos filósofos
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class SimulacaoFilosofos extends AbstractSimulacao {

    /** Número padrão de filósofos */
    public static final int NUMERO_FILOSOFOS_PADRAO = 5;
    /** Lista de filósofos */   
    private final List<Filosofo> filosofos;
    /** Lista de garfos */   
    private final List<Garfo> garfos;
    /** Indica se está executando */
    private boolean running;

    /**
     * Cria a simulação dos filósofos
     */
    public SimulacaoFilosofos() {
        super();
        running = false;
        filosofos = new LinkedList<>();
        garfos = new LinkedList<>();
    }
    
    @Override
    public void inicializa() {
        getLog().grava("Inicializando simulação...");
        filosofos.clear();
        for (int i = 0; i < NUMERO_FILOSOFOS_PADRAO; i++) {
            Filosofo filosofo = new FilosofoTeste(this);
            filosofo.setIndice(i);
            filosofos.add(filosofo);
        }
        garfos.clear();
        for (int i = 0; i < NUMERO_FILOSOFOS_PADRAO; i++) {
            garfos.add(new Garfo());
        }
    }

    @Override
    public void iniciaSimulacao() {
        running = true;
        filosofos.stream().forEach((filosofo) -> {
            filosofo.inicia();
        });
    }
    
    /**
     * Retorna um garfo
     * 
     * @param indice
     * @return Garfo
     */
    public Garfo getGarfo(int indice) {
        if (indice == -1) {
            return garfos.get(garfos.size() - 1);
        }
        return garfos.get(indice);
    }

    /**
     * Retorna a lista de filósofos
     * 
     * @return {@code List<Filosofo>}
     */
    public List<Filosofo> getFilosofos() {
        return new LinkedList<>(filosofos);
    }

    /**
     * Retorna a lista de garfos
     * 
     * @return {@code List<Garfo>}
     */
    public List<Garfo> getGarfos() {
        return garfos;
    }

    @Override
    public void encerra() {
        running = false;
        for (Filosofo filosofo : filosofos) {
            filosofo.interrupt();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }
    
}
