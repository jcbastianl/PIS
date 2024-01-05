/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;

import controlador.clases.CursaControl;
import controlador.clases.EstadoMatriculaControl;
import controlador.clases.EstudianteControl;
import controlador.clases.MatriculaControl;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import vista.modeloTablas.MatriculaModeloTabla;
import vista.utiles.UtilVista;

/**
 *
 * @author mrbingus
 */
public class FrmAdmMatricula extends javax.swing.JFrame {

    MatriculaControl matriculaControl = new MatriculaControl();
    MatriculaModeloTabla modelo = new MatriculaModeloTabla();

    private Boolean verificar() {
        return (!(txtNumeroMatricula.getText().trim().isEmpty())
                && !(txtFecha.getDate() == null)
                && !(cbxCursa.getSelectedIndex() == -1)
                && !(cbxEstado.getSelectedIndex() == -1)
                && !(cbxEstudiantes.getSelectedIndex() == -1));
    }

    private void cargarTabla() {
        modelo.setMatriculas(matriculaControl.getListaMatriculas());
        tblMatricula.setModel(modelo);
        tblMatricula.updateUI();
    }

    private void cargarCombos() {
        try {
            UtilVista.cargarcomboBoxEstudiante(cbxEstudiantes);
            UtilVista.cargarcomboBoxCursa(cbxCursa);
            UtilVista.cargarcomboBoxEstadosMatricula(cbxEstado);
        } catch (Exception e) {
        }
    }

    private void limpiar() {
        txtFecha.setDate(null);
        txtNumeroMatricula.setText("");
        cbxCursa.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
        cbxEstudiantes.setSelectedIndex(-1);

        cargarTabla();
        cargarCombos();
        tblMatricula.clearSelection();
    }

    private void guardar() {
        if (verificar()) {
            try {
                matriculaControl.getMatricula().setCursa(new CursaControl().getListaCursas().getInfo(cbxCursa.getSelectedIndex()));
                matriculaControl.getMatricula().setEstadoMatricula(cbxEstado.getSelectedItem().toString());
                matriculaControl.getMatricula().setEstudiante(new EstudianteControl().getListaEstudiantes().getInfo(cbxEstudiantes.getSelectedIndex()));

            } catch (Exception e) {
            }
            matriculaControl.getMatricula().setFechaRegistro(txtFecha.getDate());
            matriculaControl.getMatricula().setNumero(Integer.parseInt(txtNumeroMatricula.getText()));
            if (matriculaControl.persist()) {
                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar");
            }
            matriculaControl.setMatricula(null);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todos los campos");
        }
    }

    private void modificar() {
        if (verificar()) {
            try {
                matriculaControl.getMatricula().setCursa(new CursaControl().getListaCursas().getInfo(cbxCursa.getSelectedIndex()));
            } catch (Exception e) {
            }
            matriculaControl.getMatricula().setEstadoMatricula(cbxEstado.getSelectedItem().toString());
            try {
                matriculaControl.getMatricula().setEstudiante(new EstudianteControl().getListaEstudiantes().getInfo(cbxEstudiantes.getSelectedIndex()));

            } catch (Exception e) {
            }
            matriculaControl.getMatricula().setFechaRegistro(txtFecha.getDate());
            matriculaControl.getMatricula().setNumero(Integer.parseInt(txtNumeroMatricula.getText()));
            try {
                Integer indexMatricula = Utiles.encontrarPosicion("matricula", modelo.getMatriculas().getInfo(tblMatricula.getSelectedRow()).getId());

                if (matriculaControl.merge(matriculaControl.getMatricula(), indexMatricula)) {
                    JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar");
                }
            } catch (Exception e) {
            }
            matriculaControl.setMatricula(null);
            limpiar();
        }
    }

    public void borrar() {
        if (tblMatricula.getSelectedRow() > -1) {
            try {

                Integer indexMatricula = Utiles.encontrarPosicion("matricula", modelo.getMatriculas().getInfo(tblMatricula.getSelectedRow()).getId());
                if (matriculaControl.remove(indexMatricula)) {
                    JOptionPane.showMessageDialog(null, "Se eliminaron los elementos");
                } else {
                    JOptionPane.showMessageDialog(null, "No se elimin[o nada");
                }
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un elemento a eliminar");
        }

    }

    private void ordenar() {
        int t = 0;
        if (btnTipoOrden.isSelected()) {
            t = 1;
        }
        try {
            modelo.setMatriculas(matriculaControl.shellsort(matriculaControl.getListaMatriculas(), t, cbxCriterioOrden.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al ordenar " + e.getMessage() + "");
        }
        tblMatricula.setModel(modelo);
        tblMatricula.updateUI();
    }

    private void buscar() {
        try {
            modelo.setMatriculas(matriculaControl.busquedaLineal(txtBusqueda.getText(), matriculaControl.getListaMatriculas(), cbxCriterioBusqueda.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage() + "");
        }
        tblMatricula.setModel(modelo);
        tblMatricula.updateUI();
    }

    /**
     * Creates new form FrmAdmMatricula
     */
    public FrmAdmMatricula() {
        initComponents();
        limpiar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnTipoOrden = new javax.swing.JRadioButton();
        btnOrdenar = new javax.swing.JButton();
        cbxCriterioOrden = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxCriterioBusqueda = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatricula = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNumeroMatricula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxCursa = new javax.swing.JComboBox<>();
        cbxEstudiantes = new javax.swing.JComboBox<>();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("ADMINISTRAR MATRICULA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 19, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel6.setText("Fecha:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 20));

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel3.setText("Estado:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        btnBorrar.setText("DAR DE BAJA");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 204, -1, -1));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 234, 90, -1));

        btnTipoOrden.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnTipoOrden.setText("Descendente");
        getContentPane().add(btnTipoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        getContentPane().add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        cbxCriterioOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "ID" }));
        getContentPane().add(cbxCriterioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 110, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel2.setText("Matriculas registradas en el sistema:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 260, 20));

        jLabel9.setText("Criterio:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel8.setText("Criterio:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FechaRegistro", "EstadoMatricula", "ID" }));
        getContentPane().add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 110, -1));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });
        getContentPane().add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 230, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblMatricula.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMatriculaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMatricula);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 420, 170));

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel4.setText("Numero:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, 20));
        getContentPane().add(txtNumeroMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 60, -1));

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setText("Cursa:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        cbxCursa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbxCursa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 160, -1));

        cbxEstudiantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbxEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 280, -1));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "APROVADA", "REPROBADA" }));
        getContentPane().add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 160, -1));

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel10.setText("Estudiante");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 20));
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 310, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            guardar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        try {
            modificar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        try {
            borrar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        try {
            limpiar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        // TODO add your handling code here:
        try {
            ordenar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        // TODO add your handling code here:
        try {
            buscar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void tblMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatriculaMouseClicked
        // TODO add your handling code here:
        try {
            Integer indiceDocente = Utiles.encontrarPosicion("matricula", modelo.getMatriculas().getInfo(tblMatricula.getSelectedRow()).getId());
            matriculaControl.setMatricula(matriculaControl.getListaMatriculas().getInfo(indiceDocente));
            txtFecha.setDate(matriculaControl.getMatricula().getFechaRegistro());
            txtNumeroMatricula.setText(matriculaControl.getMatricula().getNumero().toString());
//            cbxCursa.setSelectedIndex(Utiles.encontrarPosicion("cursa", new CursaControl().getListaCursas().getInfo(cbxCursa.getSelectedIndex()).getId()));
//            cbxEstudiantes.setSelectedIndex(Utiles.encontrarPosicion("estudiante", new EstudianteControl().getListaEstudiantes().getInfo(cbxEstudiantes.getSelectedIndex()).getId()));
//            cbxEstado.setSelectedIndex(Utiles.encontrarPosicion("estadomatricula", new EstadoMatriculaControl().getListaEstados().getInfo(cbxEstado.getSelectedIndex()).getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_tblMatriculaMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

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
            java.util.logging.Logger.getLogger(FrmAdmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdmMatricula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JRadioButton btnTipoOrden;
    private javax.swing.JComboBox<String> cbxCriterioBusqueda;
    private javax.swing.JComboBox<String> cbxCriterioOrden;
    private javax.swing.JComboBox<String> cbxCursa;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxEstudiantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMatricula;
    private javax.swing.JTextField txtBusqueda;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNumeroMatricula;
    // End of variables declaration//GEN-END:variables
}