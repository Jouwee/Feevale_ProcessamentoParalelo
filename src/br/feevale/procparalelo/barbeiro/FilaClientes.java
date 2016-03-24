/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Fila de clientes na barbearia
 */
public class FilaClientes {

    /* Clientes que estão na fila de espera */
    private final SortedSet<Cliente> filaClientes;

    /**
     * Cria nova fila de clientes
     */
    public FilaClientes() {
        this.filaClientes = new TreeSet<>(new ClienteComparator());
    }

    /**
     * Retorna o primeiro cliente da fila
     *
     * @return primeiro cliente da fila
     */
    public synchronized Cliente getPrimeiroClienteFila() {
        return filaClientes.first();
    }

    /**
     * Remove cliente da fila
     *
     * @param cliente cliente que será removido
     * @return true se o cliente foi corretamente removido, false se houve algum
     * problema
     */
    public synchronized boolean removeClienteFila(Cliente cliente) {
        return filaClientes.remove(cliente);
    }

    /**
     * Retorna se possui algum cliente na fila
     *
     * @return true se possui, false se não possui
     */
    public synchronized boolean possuiClienteFila() {
        return filaClientes.size() > 0;
    }

    /**
     * Adiciona cliente na fila
     *
     * @param cliente cliente que será adicionado na fila
     * @return true se o cliente foi corretamente adicionado, false se houve
     * algum problema
     */
    public synchronized boolean add(Cliente cliente) {
        return filaClientes.add(cliente);
    }

    /**
     * Retorna a quantidade de clientes na fila
     *
     * @return quantidade de clientes na fila
     */
    public synchronized int size() {
        return filaClientes.size();
    }

}
