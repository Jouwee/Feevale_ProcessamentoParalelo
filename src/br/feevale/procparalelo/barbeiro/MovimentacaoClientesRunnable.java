/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe representando a movimentação de clientes na barbearia. É responsável
 * por inserir os clientes de forma aleatória na fila de espera
 */
public class MovimentacaoClientesRunnable implements Runnable {

    /** Simulação */
    private final SimulacaoBarbeiro simulacao;

    /**
     * Cria a thread 
     * 
     * @param simulacao 
     */
    public MovimentacaoClientesRunnable(SimulacaoBarbeiro simulacao) {
        this.simulacao = simulacao;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (Math.random() * 100 * simulacao.getIndiceNovosCliente()));
            } catch (InterruptedException ex) {
                Logger.getLogger(MovimentacaoClientesRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            simulacao.addNovoCliente();
        }
    }

}
