package br.feevale.procparalelo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * Panel de log
 * 
 * @author Cristian Dias, Gustavo Cassel e Nícolas Pohren
 */
public class PanelLog extends JComponent implements LogListener {
    
    /** Instância do log */
    private Log log;
    /** Panel do log */
    private JTextArea logPane;

    /**
     * Cria o painel de log
     */
    public PanelLog() {
        super();
        initGui();
        updateLog();
    }
    
    /**
     * Inicializa a interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 200));
        add(new JScrollPane(buildLogField()));
        add(buildFilter(), BorderLayout.NORTH);
    }
    
    /**
     * Cria o campo de log
     * 
     * @return JComponent
     */
    private JComponent buildLogField() {
        logPane = new JTextArea();
        DefaultCaret caret = (DefaultCaret)logPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        return logPane;
    }
    
    /**
     * Cria o painel de filtro
     * 
     * @return JComponent
     */
    private JComponent buildFilter() {
        JPanel filtros = new JPanel();
        // filtros.add(this)
        return filtros;
    }

    /**
     * Atualiza o log
     */
    private void updateLog() {
        synchronized (logPane) {
            if (log != null) {
                for (String line : log.getLines()) {
                    entryAdded(line);
                }
            }
        }
    }
    
    @Override
    public void entryAdded(String entry) {
        synchronized (logPane) {
            logPane.append(entry + "\n");
        }
    }

    
    /**
     * Retorna o log que será exibido
     * 
     * @return Log
     */
    public Log getLog() {
        return log;
    }

    /**
     * Define o log que será exibido;
     * 
     * @param log 
     */
    public void setLog(Log log) {
        if (this.log != null) {
            this.log.removeListener(this);
        }
        this.log = log;
        if (log != null) {
            this.log.addListener(this);
        }
        updateLog();
    }
    
}
