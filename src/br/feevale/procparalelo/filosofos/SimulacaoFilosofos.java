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
    private List<Filosofo> filosofos;
    /** Lista de garfos */   
    private List<Garfo> garfos;

    /**
     * Cria a simulação dos filósofos
     */
    public SimulacaoFilosofos() {
        super();
    }
    
    @Override
    public void inicializa() {
        getLog().grava("Inicializando simulação...");
        filosofos = new LinkedList<>();
        for (int i = 0; i < NUMERO_FILOSOFOS_PADRAO; i++) {
            Filosofo filosofo = new FilosofoTeste(this);
            filosofo.setIndice(i);
            filosofos.add(filosofo);
        }
        garfos = new LinkedList<>();
        for (int i = 0; i < NUMERO_FILOSOFOS_PADRAO; i++) {
            garfos.add(new Garfo());
        }
    }

    @Override
    public void iniciaSimulacao() {
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
    
}
