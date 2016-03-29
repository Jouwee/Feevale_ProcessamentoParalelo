/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe representando barbeiro
 */
public class Barbeiro implements Runnable {

    /** Referência a simulação */
    private final SimulacaoBarbeiro simulacao;
    /* Barbeiro está dormindo */
    private boolean dormindo = true;

    /**
     * 
     * @param simulacao 
     */
    public Barbeiro(SimulacaoBarbeiro simulacao) {
        this.simulacao = simulacao;
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
        new Thread(this).start();
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
        FilaClientes fila = simulacao.getFilaClientes();
        while (fila.possuiClienteFila()) {
            Cliente c = fila.getPrimeiroClienteFila();
            simulacao.getLog().grava("Cliente " + c.toString() + " começou a cortar o cabelo");
            try {
                Thread.sleep(simulacao.getTempoEsperaAtendimento());
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
            }
            simulacao.getLog().grava("Cliente " + c.toString() + " acabou o corte de cabelo");
            fila.removeClienteFila(c);
        }
        dormindo = true;
        simulacao.getLog().grava("Barbeiro começou a dormir pois não há mais clientes na barbearia");
    }

}
