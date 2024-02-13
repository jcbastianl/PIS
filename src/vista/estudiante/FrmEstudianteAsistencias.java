/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.docente;

import controlador.TDA.listas.DynamicList;
import controlador.clases.AsistenciaControl;
import controlador.clases.ClaseDictadaControl;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Asistencia;
import modelo.ClaseDictada;
import modelo.Cursa;
import vista.modeloTablas.AsistenciaModeloTabla;

/**
 *
 * @author mrbingus
 */
public class FrmDocenteAsistencais extends javax.swing.JFrame {

    AsistenciaModeloTabla modelo = new AsistenciaModeloTabla();
    AsistenciaControl asistenciaControl = new AsistenciaControl();
    ClaseDictadaControl claseControl = new ClaseDictadaControl();
    DynamicList<Asistencia> aux = asistenciaControl.all();
    Integer idcursa;

    public FrmDocenteAsistencais(Integer cursa) {
        initComponents();
        aux.reset();
        cargarTabla();
        this.idcursa = cursa;
    }

    private void generarLista() throws Exception {

        claseControl.getClase().setId_cursa(idcursa);
        claseControl.getClase().setId(claseControl.all().getLenght() + 1);
        try {
            cargarAsisntenciasgeneradas(claseControl.getClase());

        } catch (Exception e) {
        }

    }

    private void cargarTabla() {
        modelo.setAsistencias(aux);
        tblAsistencias.setModel(modelo);
        tblAsistencias.updateUI();
    }

    private void cargarAsisntenciasgeneradas(ClaseDictada c) throws Exception {
        try {
            modelo.setAsistencias(Utiles.generarAsistenciasporClase(c));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tblAsistencias.setModel(modelo);
        tblAsistencias.updateUI();
    }

    private void guardarAsistencias() throws Exception {
        for (int i = 0; i < modelo.getAsistencias().getLenght(); i++) {
            asistenciaControl.setAsistencia(modelo.getAsistencias().getInfo(i));
            try {
                asistenciaControl.persist();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el guardado");
            }
        }
        JOptionPane.showMessageDialog(null, "Se guardaron todas las asistencias");

        if (guardarClase()) {
            JOptionPane.showMessageDialog(null, "Se guardo la clase");

        } else {
            JOptionPane.showMessageDialog(null, "No se guardo la clase");

        }
    }

    private Boolean guardarClase() {
        claseControl.getClase().setTema(txtTema.getText());
        claseControl.getClase().setFecha(txtFecha.getDate());
        claseControl.getClase().setId_cursa(idcursa);

        return claseControl.persist();

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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTemaClase = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsistencias = new javax.swing.JTable();
        txtTema = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btlGenerarLista = new javax.swing.JButton();
        btnRegistrarAsistencias = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ASIGNACION DE ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 34)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ASISTENCIAS ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, -1, -1));

        HEADER.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/HEADERORANGE.png"))); // NOI18N
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 150));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        lblTemaClase.setBackground(new java.awt.Color(255, 255, 255));
        lblTemaClase.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTemaClase.setForeground(new java.awt.Color(51, 51, 51));
        lblTemaClase.setText("NUEVA CLASE");
        jPanel3.add(lblTemaClase, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1280, 40));

        tblAsistencias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAsistencias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 870, 420));
        jPanel1.add(txtTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 650, -1));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 370, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Fecha:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 20));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Tema:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 20));

        btlGenerarLista.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btlGenerarLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/icons8-flecha-de-descenso-30.png"))); // NOI18N
        btlGenerarLista.setText("GENERAR LISTA");
        btlGenerarLista.setContentAreaFilled(false);
        btlGenerarLista.setHideActionText(true);
        btlGenerarLista.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btlGenerarLista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btlGenerarLista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btlGenerarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlGenerarListaActionPerformed(evt);
            }
        });
        jPanel1.add(btlGenerarLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 210, 130, 60));

        btnRegistrarAsistencias.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegistrarAsistencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnRegistrarAsistencias.setText("   GUARDAR REGISTRO");
        btnRegistrarAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAsistenciasActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrarAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 290, 250, 70));

        btnExit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/icons8-visible-30.png"))); // NOI18N
        btnExit.setText("REVISAR JUSTIFICANTE");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 400, 250, 70));

        btnExit1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
        btnExit1.setText("        SALIR       ");
        btnExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExit1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 510, 250, 70));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btlGenerarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlGenerarListaActionPerformed
        // TODO add your handling code here:
        try {
            generarLista();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btlGenerarListaActionPerformed

    private void btnRegistrarAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAsistenciasActionPerformed
        try {
            guardarAsistencias();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarAsistenciasActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExit1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnExit1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDocenteAsistencais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDocenteAsistencais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDocenteAsistencais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDocenteAsistencais.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new FrmDocenteAsistencais(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HEADER;
    private javax.swing.JButton btlGenerarLista;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnRegistrarAsistencias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTemaClase;
    private javax.swing.JTable tblAsistencias;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtTema;
    // End of variables declaration//GEN-END:variables
}
