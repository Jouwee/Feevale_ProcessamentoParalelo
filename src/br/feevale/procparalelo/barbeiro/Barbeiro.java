/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Classe representando barbeiro
 */
public class Barbeiro extends Thread {

    /** Referência a simulação */
    private final SimulacaoBarbeiro simulacao;
    /** Objeto de lock */
    private final Object lock;
    /* Barbeiro está dormindo */
    private boolean dormindo = true;

    /**
     * 
     * @param simulacao 
     */
    public Barbeiro(SimulacaoBarbeiro simulacao) {
        this.simulacao = simulacao;
        lock = new Object();
    }
    
    /**
     * Acorda o barbeiro se ele estiver dormindo
     */
    public synchronized void acordaSeNecessario() {
        if (!dormindo) {
            return;
        }
        dormindo = false;
        simulacao.getLog().grava("Barbeiro foi acordado");
        synchronized(lock) {
            lock.notify();
        }
    }

    /**
     * Retorna se o barbeiro está dormindo
     * 
     * @return boolean
     */
    public boolean isDormindo() {
        return dormindo;
    }

    @Override
    public void run() {
        try {
            FilaClientes fila = simulacao.getFilaClientes();
            while (true) {
                while (fila.possuiClienteFila()) {
                    Cliente c = fila.getPrimeiroClienteFila();
                    simulacao.getLog().grava("Cliente " + c.toString() + " começou a cortar o cabelo");
                    fila.removeClienteFila(c);
                    Thread.sleep(simulacao.getTempoEsperaAtendimento());
                    simulacao.getLog().grava("Cliente " + c.toString() + " acabou o corte de cabelo");
                }
                dormindo = true;
                simulacao.getLog().grava("Barbeiro começou a dormir pois não há clientes na barbearia");
                synchronized(lock) {
                    lock.wait();
                }
            }
        } catch (InterruptedException ex) {
        }
    }

}
