package br.feevale.procparalelo;

import br.feevale.procparalelo.filosofos.PanelSimulacaoFilosofos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/**
 * Classe principal da aplicação
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class Aplicacao extends JFrame {

    /** Panels de simulação */
    private final Map<Integer, PanelSimulacao> panelsSimulacao;
    /** Abas da aplicação */
    private JTabbedPane abas;
    /** Painel de propriedades específicas */
    private JPanel panelPropriedadesEspecificas;
    /** Botão de iniciar */
    private JButton botaoIniciar;
    /** Botão de parar */
    private JButton botaoParar;
    
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
        panelsSimulacao = new HashMap<>();
        initGui();
        onAbaChanged();
    }
    
    /**
     * Inicializa a interface
     */
    private void initGui() {
        setupFrame();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buildAbas());
        getContentPane().add(buildPropriedades(), BorderLayout.EAST);
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
     * Cria o painel de propriedades
     * 
     * @return JComponent
     */
    private JComponent buildPropriedades() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setLayout(new BorderLayout());
        panel.add(buildPropriedadesEspecificas());
        panel.add(buildPanelControle(), BorderLayout.SOUTH);
        return panel;
    }
    
    /**
     * Cria o painel das propriedades especificas
     * 
     * @return JCompoentn
     */
    private JComponent buildPropriedadesEspecificas() {
        panelPropriedadesEspecificas = new JPanel(new BorderLayout());
        return panelPropriedadesEspecificas;
    }
    
    /**
     * 
     * @return 
     */
    private JComponent buildPanelControle() {
        JPanel panel = new JPanel();
        panel.add(buildBotaoIniciar());
        panel.add(buildBotaoParar());
        return panel;
    }

    /**    
     * Cria o botão de iniciar
     * 
     * @return JComponent
     */
    private JComponent buildBotaoIniciar() {
        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.addActionListener((ActionEvent e) -> {
            iniciarSimulacao();
        });
        return botaoIniciar;
    }

    /**    
     * Cria o botão de parar
     * 
     * @return JComponent
     */
    private JComponent buildBotaoParar() {
        botaoParar = new JButton("Parar");
        botaoParar.addActionListener((ActionEvent e) -> {
            pararSimulacao();
        });
        return botaoParar;
    }
    
    /**
     * Iniciar simulação
     */
    private void iniciarSimulacao() {
        getPanelSimulacaco().getSimulacao().executa();
        updateButtons();
    }
    
    /**
     * Iniciar simulação
     */
    private void pararSimulacao() {
        getPanelSimulacaco().getSimulacao().encerra();
        updateButtons();
    }
    
    /**
     * Adiciona uma nova aba
     * 
     * @param title
     * @param painel 
     */
    public void addAba(String title, PanelSimulacao painel) {
        abas.add(title, painel.getPainelPrincipal());
        panelsSimulacao.put(abas.getTabCount() - 1, painel);
    }
    
    /**
     * Atualiza a interface baseada na aba
     */
    public void onAbaChanged() {
        PanelSimulacao pSimulacao = getPanelSimulacaco();
        panelPropriedadesEspecificas.add(pSimulacao.getPainelPropriedades());
        updateButtons();
    }
    
    /**
     * Atualiza os botões
     */
    public void updateButtons() {
        AbstractSimulacao simulacao = getPanelSimulacaco().getSimulacao();
        botaoIniciar.setEnabled(!simulacao.isRunning());
        botaoParar.setEnabled(simulacao.isRunning());
    }
    
    /**
     * Retorna o painel de simulação
     * 
     * @return PanelSimulacao
     */
    public PanelSimulacao getPanelSimulacaco() {
        return panelsSimulacao.get(abas.getSelectedIndex());
    }
    
}
