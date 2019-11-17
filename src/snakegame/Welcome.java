/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author Lokesh Chandra
 */
public class Welcome extends javax.swing.JFrame {

    /**
     * Creates new form Welcome
     */
   
    public Welcome() {
        initComponents();
        MakeOffline mo = new MakeOffline();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namebox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        highscorebox = new javax.swing.JTextField();
        multiplaybtn = new javax.swing.JButton();
        sinlgeplaybtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("HP Simplified Light", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome,");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        namebox.setEditable(false);
        namebox.setBackground(new java.awt.Color(51, 51, 51));
        namebox.setFont(new java.awt.Font("HP Simplified Light", 1, 32)); // NOI18N
        namebox.setForeground(new java.awt.Color(255, 153, 0));
        namebox.setBorder(null);
        jPanel1.add(namebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 380, 60));

        jLabel2.setFont(new java.awt.Font("HP Simplified Light", 0, 18)); // NOI18N
        jLabel2.setText("High Score :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 90, 30));

        highscorebox.setEditable(false);
        highscorebox.setBackground(new java.awt.Color(51, 51, 51));
        highscorebox.setFont(new java.awt.Font("HP Simplified Light", 1, 14)); // NOI18N
        highscorebox.setForeground(new java.awt.Color(255, 255, 0));
        highscorebox.setBorder(null);
        jPanel1.add(highscorebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 110, 30));

        multiplaybtn.setBackground(new java.awt.Color(51, 51, 51));
        multiplaybtn.setFont(new java.awt.Font("HP Simplified Light", 1, 24)); // NOI18N
        multiplaybtn.setForeground(new java.awt.Color(51, 255, 0));
        multiplaybtn.setText("Multi Play");
        multiplaybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplaybtnActionPerformed(evt);
            }
        });
        jPanel1.add(multiplaybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 330, 40));

        sinlgeplaybtn.setBackground(new java.awt.Color(51, 51, 51));
        sinlgeplaybtn.setFont(new java.awt.Font("HP Simplified Light", 1, 24)); // NOI18N
        sinlgeplaybtn.setForeground(new java.awt.Color(51, 255, 0));
        sinlgeplaybtn.setText("Single Play");
        sinlgeplaybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinlgeplaybtnActionPerformed(evt);
            }
        });
        jPanel1.add(sinlgeplaybtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 330, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void multiplaybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplaybtnActionPerformed
        // TODO add your handling code here:
        MultiPlay mp = new MultiPlay();
        mp.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_multiplaybtnActionPerformed
    static String playstatus;
    private void sinlgeplaybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinlgeplaybtnActionPerformed
        // TODO add your handling code here:
       
        playstatus="Single Play";
        SnakeGame sg = new SnakeGame();
        sg.playstatusbox.setText("Single Play");
        sg.player1box.setText(Login.uid);
        sg.setVisible(true);
        dispose();
    }//GEN-LAST:event_sinlgeplaybtnActionPerformed

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
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField highscorebox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton multiplaybtn;
    public javax.swing.JTextField namebox;
    private javax.swing.JButton sinlgeplaybtn;
    // End of variables declaration//GEN-END:variables
}
