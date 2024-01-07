/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;

/**
 *
 * @author mrbingus
 */
public class FrmAdmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmAdmPrincipal
     */
    public FrmAdmPrincipal() {
        initComponents();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnDocentes = new javax.swing.JButton();
        btnMatriculas = new javax.swing.JButton();
        btnAsignaturas = new javax.swing.JButton();
        btnCursas = new javax.swing.JButton();
        btnEstudiantes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ADMINISTRACION");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MÓDULO DE");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 40, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/HEADER.jpg"))); // NOI18N
        HEADER.setText("jLabel7");
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 150));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 800, 10));

        btnDocentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/ImgDocentes.jpg"))); // NOI18N
        btnDocentes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDocentes.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnDocentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocentesActionPerformed(evt);
            }
        });
        jPanel1.add(btnDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 110, 330));

        btnMatriculas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/ImgMatricula.jpg"))); // NOI18N
        btnMatriculas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMatriculas.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnMatriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatriculasActionPerformed(evt);
            }
        });
        jPanel1.add(btnMatriculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 110, 330));

        btnAsignaturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/ImgAsignatura.jpg"))); // NOI18N
        btnAsignaturas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAsignaturas.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignaturasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 110, 330));

        btnCursas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/ImgCursas.jpg"))); // NOI18N
        btnCursas.setToolTipText("");
        btnCursas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCursas.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnCursas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCursasActionPerformed(evt);
            }
        });
        jPanel1.add(btnCursas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, 330));

        btnEstudiantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/ImgEstudiantes.jpg"))); // NOI18N
        btnEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEstudiantes.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudiantesActionPerformed(evt);
            }
        });
        jPanel1.add(btnEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 110, 330));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Estudiantes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 540, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Cursos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Matriculas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Asignaturas");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Docentes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCursasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCursasActionPerformed
        new FrmAdmCursa().setVisible(true);
    }//GEN-LAST:event_btnCursasActionPerformed

    private void btnMatriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatriculasActionPerformed
        new FrmAdmMatricula().setVisible(true);
    }//GEN-LAST:event_btnMatriculasActionPerformed

    private void btnAsignaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignaturasActionPerformed
        new FrmAdmAsignatura().setVisible(true);
    }//GEN-LAST:event_btnAsignaturasActionPerformed

    private void btnDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocentesActionPerformed
        new FrmAdmDocente().setVisible(true);
    }//GEN-LAST:event_btnDocentesActionPerformed

    private void btnEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudiantesActionPerformed
        new FrmAdmEstudiante().setVisible(true);
    }//GEN-LAST:event_btnEstudiantesActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HEADER;
    private javax.swing.JButton btnAsignaturas;
    private javax.swing.JButton btnCursas;
    private javax.swing.JButton btnDocentes;
    private javax.swing.JButton btnEstudiantes;
    private javax.swing.JButton btnMatriculas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}