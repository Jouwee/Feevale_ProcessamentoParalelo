package br.feevale.procparalelo.barbeiro;

import br.feevale.procparalelo.AbstractSimulacao;

/**
 * Simulação do barbeiro
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class SimulacaoBarbeiro extends AbstractSimulacao {

    /** Tamanho da sala de espera padrão */
    private final static int DEFAULT_TAMANHO_SALA_ESPERA = 5;
    /* Fila de clientes na barbearia */
    private final FilaClientes filaClientes;
    /* Barbeiro que atende na barbearia */
    private Barbeiro barbeiro;
    /** Tamanho da sala de espera */
    private int tamanhoSalaEspera;
    /* Índice para calcular o tempo entre a chegada de cada cliente */
    private long indiceNovosCliente = 20;
    /* Tempo (em milisegundos) que o barbeiro leva para cortar o cabelo */
    private long tempoEsperaAtendimento = 2000;
    /** Controle de se a simulação está sendo executada */
    private boolean running;
    /** Movimentação de clientes da barbearia */
    private Thread movimentacaoClientes;


    /**
     * Cria uma nova simulação do barbeiro
     */
    public SimulacaoBarbeiro() {
        this.filaClientes = new FilaClientes();
        tamanhoSalaEspera = DEFAULT_TAMANHO_SALA_ESPERA;
    }
    
    @Override
    public void inicializa() {
        movimentacaoClientes = new Thread(new MovimentacaoClientesRunnable(this));
        barbeiro = new Barbeiro(this);
    }

    @Override
    public void iniciaSimulacao() {
        running = true;
        movimentacaoClientes.start();
        barbeiro.start();
    }

    @Override
    public void encerra() {
        movimentacaoClientes.interrupt();
        barbeiro.interrupt();
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * Adiciona novo cliente à barbearia
     */
    public synchronized void addNovoCliente() {
        Cliente c = new Cliente();
        if (filaClientes.size() == tamanhoSalaEspera) {
            getLog().grava("Cliente " + c.toString() + " está indo embora pois a barbearia está cheia");
            return;
        }
        getLog().grava("Cliente " + c.toString() + " chegou à barbearia");
        filaClientes.add(c);
        barbeiro.acordaSeNecessario();
    }

    /**
     * Retorna o barbeiro que trabalha na barbearia
     *
     * @return barbeiro que trabalha na barbearia
     */
    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    /**
     * Retorna a fila de clientes
     *
     * @return fila de clientes
     */
    public FilaClientes getFilaClientes() {
        return filaClientes;
    }

    /**
     * Retorna o tamanho da sala de espera
     * 
     * @return int
     */
    public int getTamanhoSalaEspera() {
        return tamanhoSalaEspera;
    }

    /**
     * Define o tamanho da sala de espera
     * 
     * @param tamanhoSalaEspera 
     */
    public void setTamanhoSalaEspera(int tamanhoSalaEspera) {
        this.tamanhoSalaEspera = tamanhoSalaEspera;
    }

    /**
     * Retorna índice para calcular o tempo entre a chegada de cada cliente
     *
     * @return índice para calcular o tempo entre a chegada de cada cliente
     */
    public long getIndiceNovosCliente() {
        return indiceNovosCliente;
    }

    /**
     * Indica indice para calcular o tempo entre a chegada de cada cliente
     *
     * @param indiceNovosCliente índice para calcular o tempo entre a chegada de
     * cada cliente
     */
    public void setIndiceNovosCliente(long indiceNovosCliente) {
        this.indiceNovosCliente = indiceNovosCliente;
    }

    /**
     * Retorna tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     *
     * @return Tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     */
    public long getTempoEsperaAtendimento() {
        return tempoEsperaAtendimento;
    }

    /**
     * Indica tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     *
     * @param tempoEsperaAtendimento Tempo (em milisegundos) que o barbeiro leva
     * para cortar o cabelo
     */
    public <T> void setTempoEsperaAtendimento(long tempoEsperaAtendimento) {
        this.tempoEsperaAtendimento = tempoEsperaAtendimento;
    }


}
