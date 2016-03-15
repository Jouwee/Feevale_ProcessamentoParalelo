package br.feevale.procparalelo;

import br.feevale.procparalelo.filosofos.PanelSimulacaoFilosofos;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 * Classe principal da aplicação
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Aplicacao extends JFrame {
    
    /** Abas da aplicação */
    private JTabbedPane abas;
    
    /**
     * Método principal da aplicação
     * 
     * @param args 
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setVisible(true);
    }
    
    /**
     * Cria a aplicação
     */
    public Aplicacao() {
        super();
        initGui();
    }
    
    /**
     * Inicializa a interface
     */
    private void initGui() {
        setupFrame();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buildAbas());
    }
    
    /**
     * Configura o frame
     */
    private void setupFrame() {
        setTitle("Processamento paralelo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Cria as abas da aplicação
     * 
     * @return JComponent
     */
    private JComponent buildAbas() {
        abas = new JTabbedPane();
        addAba("Problema dos filósofos", new PanelSimulacaoFilosofos());
        abas.setFont(new Font("Calibri", Font.BOLD, 14));
        return abas;
    }
    
    /**
     * Adiciona uma nova aba
     * 
     * @param title
     * @param painel 
     */
    public void addAba(String title, PanelSimulacao painel) {
        abas.add(title, painel.getPainelPrincipal());
    }
    
}
