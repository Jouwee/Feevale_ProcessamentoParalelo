/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Métodos utilitários do cliente da barbearia
 */
public class ClienteUtil {

    /* Código do último cliente que veio à barbearia */
    private static long codUltimoCliente;

    /**
     * Gera novo código de cliente
     *
     * @return novo código de cliente
     */
    public static long geraNovoCodCliente() {
        return ++codUltimoCliente;
    }

}
