/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Configuraçoẽs do programa de Barbeiro Dorminhoco
 */
public class Configuracoes {

    /* Índice para calcular o tempo entre a chegada de cada cliente */
    private static long indiceNovosCliente = 20;
    /* Tempo (em milisegundos) que o barbeiro leva para cortar o cabelo */
    private static long tempoEsperaAtendimento = 2000;

    /**
     * Retorna índice para calcular o tempo entre a chegada de cada cliente
     *
     * @return índice para calcular o tempo entre a chegada de cada cliente
     */
    public static long getIndiceNovosCliente() {
        return indiceNovosCliente;
    }

    /**
     * Indica indice para calcular o tempo entre a chegada de cada cliente
     *
     * @param indiceNovosCliente índice para calcular o tempo entre a chegada de
     * cada cliente
     */
    public static void setIndiceNovosCliente(long indiceNovosCliente) {
        Configuracoes.indiceNovosCliente = indiceNovosCliente;
    }

    /**
     * Retorna tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     *
     * @return Tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     */
    public static long getTempoEsperaAtendimento() {
        return tempoEsperaAtendimento;
    }

    /**
     * Indica tempo (em milisegundos) que o barbeiro leva para cortar o cabelo
     *
     * @param tempoEsperaAtendimento Tempo (em milisegundos) que o barbeiro leva
     * para cortar o cabelo
     */
    public static void setTempoEsperaAtendimento(long tempoEsperaAtendimento) {
        Configuracoes.tempoEsperaAtendimento = tempoEsperaAtendimento;
    }

}
