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
    /** Taxa da redução de starvation default */
    public static final double TAXA_REDUCAO_STARVATION_PADRAO = .1;
    /** Taxa da redução de starvation default */
    public static final double TAXA_RESTAURACAO_STARVATION_PADRAO = .5;
    /** Lista de filósofos */   
    private final List<Filosofo> filosofos;
    /** Lista de garfos */   
    private final List<Garfo> garfos;
    /** Número de filosofos da simulação */
    private int quantidadeFilosofos;
    /** Taxa de redução do starvation */
    private double taxaReducaoStarvation;
    /** Taxa de restauração do starvation */
    private double restauracaoStarvation;
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
        quantidadeFilosofos = NUMERO_FILOSOFOS_PADRAO;
        taxaReducaoStarvation = TAXA_REDUCAO_STARVATION_PADRAO;
        restauracaoStarvation = TAXA_RESTAURACAO_STARVATION_PADRAO;
    }
    
    @Override
    public void inicializa() {
        getLog().grava("Inicializando simulação...");
        filosofos.clear();
        for (int i = 0; i < quantidadeFilosofos; i++) {
            Filosofo filosofo = new Filosofo(this);
            filosofo.setIndice(i);
            filosofos.add(filosofo);
        }
        garfos.clear();
        for (int i = 0; i < quantidadeFilosofos; i++) {
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

    /**
     * Retorna a quantidade de filósofos
     * 
     * @return int
     */
    public int getQuantidadeFilosofos() {
        return quantidadeFilosofos;
    }

    /**
     * Define a quantidade de filósofos
     * 
     * @param quantidadeFilosofos 
     */
    public void setQuantidadeFilosofos(int quantidadeFilosofos) {
        this.quantidadeFilosofos = quantidadeFilosofos;
    }

    /**
     * Retorna a taxa de redução do Starvation
     * 
     * @return int
     */
    public double getTaxaReducaoStarvation() {
        return taxaReducaoStarvation;
    }

    /**
     * Define a taxa da redução do starvation
     * 
     * @param taxaReducaoStarvation 
     */
    public void setTaxaReducaoStarvation(double taxaReducaoStarvation) {
        this.taxaReducaoStarvation = taxaReducaoStarvation;
    }

    /**
     * Retorna a taxa de restauração do starvation
     * 
     * @return double
     */
    public double getRestauracaoStarvation() {
        return restauracaoStarvation;
    }

    /**
     * Define a taxa de restauração do starvation
     * 
     * @param restauracaoStarvation 
     */
    public void setRestauracaoStarvation(double restauracaoStarvation) {
        this.restauracaoStarvation = restauracaoStarvation;
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
