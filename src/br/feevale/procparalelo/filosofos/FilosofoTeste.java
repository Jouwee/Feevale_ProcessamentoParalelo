package br.feevale.procparalelo.filosofos;

/**
 * Implementação de teste de um filósofo
 * 
 * @author 0118194
 */
public class FilosofoTeste extends Filosofo {

    public FilosofoTeste(SimulacaoFilosofos simulacao) {
        super(simulacao);
    }

    @Override
    public void loop() throws InterruptedException, GarfoEmUsoException {
        if (!isGarfoDireitoEmUso()) {
            try {
                pegaGarfoDireito();
                if (!isGarfoEsquerdoEmUso()) {
                    try {
                        pegaGarfoEsquerdo();
                        comer(1);
                    } catch (GarfoEmUsoException e) {}
                    largaGarfoEsquerdo();
                }
            } catch (GarfoEmUsoException e) {}
            largaGarfoDireito();
        }
        espera(0.1);
    }
    
}
