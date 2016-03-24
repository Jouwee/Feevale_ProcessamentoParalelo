/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

import java.util.Comparator;

/**
 * Comparator para ordenar os clientes da barbearia pelo código
 */
public class ClienteComparator implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return new Long(c1.getCodigo()).compareTo(c2.getCodigo());
    }

}
