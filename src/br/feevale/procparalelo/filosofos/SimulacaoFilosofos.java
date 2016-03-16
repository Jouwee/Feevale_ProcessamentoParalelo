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
            Filosofo filosofo = new FilosofoTeste();
            filosofos.add(filosofo);
        }
    }

    @Override
    public void iniciaSimulacao() {
        filosofos.stream().forEach((filosofo) -> {
            filosofo.inicia();
        });
    }
    
    
    
}
