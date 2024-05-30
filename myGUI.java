/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author user
 */
public class myGUI extends javax.swing.JFrame implements ActionListener{
    JButton jButton1;
    String email;
    /**
     * Creates new form myGUI
     */
    public myGUI(String email) {
        initComponents();
        this.email = email;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        
        jButton1= new javax.swing.JButton();
        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setText("Save and Continue");
        getContentPane().add(jButton1);
        jButton1.setBounds(720, 360, 150, 40);
        jButton1.addActionListener(this);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Inria Serif", 1, 18)); // NOI18N
        jLabel1.setText("Update Your Security Information");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 76, 332, 22);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("If there is ever a problem with your account, this information will make it easier for you to log back in and connect with your friends.");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 129, 1041, 31);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Security question");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(57, 172, 208, 17);

        jLabel4.setText("Choose a security question that only you can answer.");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(83, 195, 317, 27);

        String[] securityQuestions = {
            "What is the name of your first pet?",
            "What was the name of your first school?",
            "What is your favorite book?",
            "In what city were you born?",
            "What is your favorite movie?",
            "What was your childhood nickname?",
            "What is your favorite food?"
        };

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(securityQuestions));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(94, 240, 306, 22);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(94, 280, 306, 30);

        setBounds(100,100,900,600);
    }// </editor-fold>                        

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(myGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new myGUI("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            if(jTextField1.getText()==""){
                JOptionPane.showMessageDialog(null,"Field Required");

            }
            else{
                conn conn = new conn();
                String answer = jTextField1.getText();
                String securityQuestion = (String)jComboBox1.getSelectedItem();
                
                String query = "Create table if not exists security(email varchar(255) primary key,security_question varchar(255), answer varchar(255))";
                conn.s.executeUpdate(query);
                
                String query1 = "Insert into security values('"+email+"','"+securityQuestion+"','"+answer+"')";
                conn.s.executeUpdate(query1);

                new login().setVisible(true);
            }
        }
        catch (Exception e1){
            System.out.println(e1);
        }
        
    }
}
