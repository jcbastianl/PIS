/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.estudiante;

import controlador.DAO.implementaciones.AsignaturaImplementacion;
import controlador.clases.AsignaturaControl;
import vista.docente.*;
import controlador.clases.CursaControl;
import controlador.clases.EstudianteControl;
import controlador.utiles.Utiles;
import modelo.Cursa;
import modelo.Estudiante;
import vista.modeloTablas.CursaModeloTabla;
import vista.modeloTablas.EstudianteCursaModeloTabla;

/**
 *
 * @author mrbingus
 */
public class FrmEstudiantePrincipal extends javax.swing.JFrame {

    EstudianteCursaModeloTabla modelo = new EstudianteCursaModeloTabla();
    Estudiante estudiante;

    /**
     * @param estu
     */
    public FrmEstudiantePrincipal(Estudiante estu) {
        initComponents();
        this.estudiante = estu;
        cargarTablua();
        lblNombre.setText(estu.getNombre() + " " + estu.getApellido());
    }

    private void cargarTablua() {
        try {
            modelo.setListaCursos(Utiles.recuperarCursasEstudiante(estudiante.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tblCursos.setModel(modelo);
        tblCursos.updateUI();

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
                jPanel2 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                lblNombre = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tblCursos = new javax.swing.JTable();
                jLabel9 = new javax.swing.JLabel();
                HEADER = new javax.swing.JLabel();
                btnExit = new javax.swing.JButton();
                btnAcceder = new javax.swing.JButton();
                jLabel10 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

                jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1280, 10));

                jLabel8.setBackground(new java.awt.Color(255, 255, 255));
                jLabel8.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                jLabel8.setText("ESTUDIANTE");
                jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, -1, -1));

                lblNombre.setBackground(new java.awt.Color(255, 255, 255));
                lblNombre.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
                lblNombre.setText("ESTUDIANTE:");
                jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, -1, -1));

                tblCursos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
                tblCursos.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                        },
                        new String [] {
                                "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                ));
                jScrollPane1.setViewportView(tblCursos);

                jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 920, 450));

                jLabel9.setBackground(new java.awt.Color(255, 255, 255));
                jLabel9.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                jLabel9.setText("MÓDULO ");
                jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, -1, -1));

                HEADER.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/HEADERGREEN.png"))); // NOI18N
                HEADER.setText("jLabel7");
                HEADER.setAutoscrolls(true);
                HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 150));

                btnExit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
                btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
                btnExit.setText("        SALIR");
                btnExit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnExitActionPerformed(evt);
                        }
                });
                jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 360, 250, 70));

                btnAcceder.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
                btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/icons8-entrar-30.png"))); // NOI18N
                btnAcceder.setText("       ACCEDER");
                btnAcceder.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAccederActionPerformed(evt);
                        }
                });
                jPanel1.add(btnAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 240, 250, 70));

                jLabel10.setBackground(new java.awt.Color(255, 255, 255));
                jLabel10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
                jLabel10.setText("ASIGNATURAS:");
                jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

                getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
        // TODO add your handling code here:
        Cursa c;

        try {
            c = Utiles.recuperarCursasEstudiante(estudiante.getId()).getInfo(tblCursos.getSelectedRow());
            new FrmEstudianteAsistencias(estudiante.getId(), c.getId(), new AsignaturaImplementacion().all().getInfo(Utiles.encontrarPosicion("asignatura", c.getAsignatura())).getNombre()).setVisible(true);
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAccederActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEstudiantePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiantePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiantePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiantePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstudiantePrincipal(null).setVisible(true);
            }
        });
    }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel HEADER;
        private javax.swing.JButton btnAcceder;
        private javax.swing.JButton btnExit;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel lblNombre;
        private javax.swing.JTable tblCursos;
        // End of variables declaration//GEN-END:variables
}
