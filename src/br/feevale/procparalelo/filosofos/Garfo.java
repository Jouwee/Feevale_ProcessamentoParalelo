package br.feevale.procparalelo.filosofos;

/**
 * Representação do garfo
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Garfo {

    /** Filósofo que está utilizando o garfo */
    private Filosofo filosofo;

    /**
     * Retorna o filósofo que está utilizando este garfo
     * 
     * @return Filosofo
     */
    public synchronized Filosofo getFilosofo() {
        return filosofo;
    }

    /**
     * Limpa o filósofo
     * 
     * @param caller 
     */
    public synchronized void clearFilosofo(Filosofo caller) {
       if (caller != filosofo) {
           return;
       }
       filosofo = null;
    }
    
    /**
     * Define o filósofo que está usando o garfo
     * 
     * @param filosofo 
     * @throws br.feevale.procparalelo.filosofos.GarfoEmUsoException 
     */
    public synchronized void setFilosofo(Filosofo filosofo) throws GarfoEmUsoException {
        if (filosofo == null) {
            throw new IllegalArgumentException("O filósofo não pode ser null. Use o método clearFilosofo");
        }
        if (this.filosofo != null) {
            throw new GarfoEmUsoException();
        }
        this.filosofo = filosofo;
    }
    
}
