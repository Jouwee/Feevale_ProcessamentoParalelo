/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Cliente da barbearia
 */
public class Cliente {

    /* Código do cliente */
    private final long codigo;

    /**
     * Cria objeto representando cliente da barbearia
     */
    public Cliente() {
        this.codigo = ClienteUtil.geraNovoCodCliente();
    }

    /**
     * Retorna o código do cliente
     *
     * @return código do cliente
     */
    public long getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return Long.toString(codigo);
    }

}
