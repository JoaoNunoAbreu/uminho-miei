/*
 * DISCLAIMER: Este código foi criado para discussão e edição durante as aulas 
 * práticas de DSS, representando uma solução em construção. Como tal, não deverá 
 * ser visto como uma solução canónica, ou mesmo acabada. É disponibilizado para 
 * auxiliar o processo de estudo. Os alunos são encorajados a testar adequadamente 
 * o código fornecido e a procurar soluções alternativas, à medida que forem 
 * adquirindo mais conhecimentos.
 */
package dss.sgq;

import javax.swing.SwingUtilities;
import dss.pubsub.DSSObserver;
import dss.pubsub.DSSObservable;

/*
 * JCalculadora.java
 *
 * Created on March 27, 2004, 3:49 PM
 */

/**
 *
 * @author  jfc
 */
public class SGQView extends javax.swing.JFrame implements DSSObserver {
    
    /** A calculadora que vai fazer as contas... */
    private SGQController controller; 
    
    /** Creates new form JCalculadora */
    public SGQView(SGQController model) {
        this.controller = model;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.META_MASK));
        jMenuItem1.setText("Novo Sócio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novo_socio(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void novo_socio(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novo_socio
        
    }//GEN-LAST:event_novo_socio
    
    /**
     * @param args the command line arguments
     */
    public void run() {
            
        this.initComponents();
        this.setVisible(true);
        
    }
    
    /**
     * Método correspondente à interface Observer.
     * Este é o método que é invocado sempre que a calculadora efectua um 
     * notifyObservers, actualiza o écran com o valor que vem como parâmetro 
     */
    public void update(DSSObservable o, Object arg) {
        this.screen.setText((arg.toString()));
    }    
    
    public void exit() {
        System.exit(1);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
    
}
