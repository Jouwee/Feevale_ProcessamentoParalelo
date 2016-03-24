/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Classe representando barbearia
 */
public class Barbearia {

    /* Quantidade máxima de cadeiras disponíveis para os clientes esperarem atendimento */
    public static int MAX_CLIENTES = 5;

    /* Fila de clientes na barbearia */
    private final FilaClientes filaClientes;
    /* Barbeiro que atende na barbearia */
    private final Barbeiro barbeiro;
    /* Instância própria do singleton */
    private static Barbearia self;

    /**
     * Cria nova Barbearia
     */
    private Barbearia() {
        this.barbeiro = new Barbeiro();
        this.filaClientes = new FilaClientes();
    }

    /**
     * Retorna instância própria do singleton
     *
     * @return instância própria do singleton
     */
    public static Barbearia getInstance() {
        if (self == null) {
            self = new Barbearia();
        }
        return self;
    }

    /**
     * Inicia o atendimento na barbearia
     */
    public void iniciaBarbearia() {
        new Thread(new MovimentacaoClientesRunnable()).start();
    }

    /**
     * Adiciona novo cliente à barbearia
     */
    public synchronized void addNovoCliente() {
        Cliente c = new Cliente();
        if (filaClientes.size() == MAX_CLIENTES) {
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

}
