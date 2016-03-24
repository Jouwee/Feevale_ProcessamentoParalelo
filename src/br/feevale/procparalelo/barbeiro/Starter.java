/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.feevale.procparalelo.barbeiro;

/**
 * Inicia programa do Barbeiro Dorminhoco
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 0) {
            System.out.println("Indicada quantidade inválida de parâmetros! ");
            System.out.println("Execução será abortada");
            System.out.println();
            System.out.println("Param 1 deve ser: índice para calcular o tempo entre a chegada de cada cliente");
            System.out.println("Param 2 deve ser: tempo (em milisegundos) que o barbeiro leva para cortar o cabelo");
            return;
        }
        if (args.length == 2) {
            try {
                Configuracoes.setIndiceNovosCliente(Long.parseLong(args[0]));
                Configuracoes.setTempoEsperaAtendimento(Long.parseLong(args[1]));
            } catch (Exception e) {
                System.out.println("As configurações são inválidas! Necessário indicar valores tipo Long");
                System.out.println("Execução será abortada");
                return;
            }
        }
        System.out.println("===========CONFIGURAÇÕES DO PROGRAMA===========");
        System.out.println("Índice para calcular o tempo entre a chegada de cada cliente: " + Configuracoes.getIndiceNovosCliente());
        System.out.println("Tempo (em milisegundos) que o barbeiro leva para cortar o cabelo: " + Configuracoes.getTempoEsperaAtendimento());
        System.out.println("===============================================");
        Barbearia.getInstance().iniciaBarbearia();
    }

}
