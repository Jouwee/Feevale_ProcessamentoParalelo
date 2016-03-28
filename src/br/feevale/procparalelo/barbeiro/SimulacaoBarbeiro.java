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
    private final Barbeiro barbeiro;
    /** Tamanho da sala de espera */
    private int tamanhoSalaEspera;
    /** Controle de se a simulação está sendo executada */
    private boolean running;


    /**
     * Cria uma nova simulação do barbeiro
     */
    public SimulacaoBarbeiro() {
        this.barbeiro = new Barbeiro(this);
        this.filaClientes = new FilaClientes();
    }
    
    @Override
    public void inicializa() {
        tamanhoSalaEspera = DEFAULT_TAMANHO_SALA_ESPERA;
    }

    @Override
    public void iniciaSimulacao() {
        running = true;
        new Thread(new MovimentacaoClientesRunnable(this)).start();
    }

    @Override
    public void encerra() {
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
            System.out.println("Cliente " + c.toString() + " está indo embora pois a barbearia está cheia");
            return;
        }
        System.out.println("Cliente " + c.toString() + " chegou à barbearia");
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

}
