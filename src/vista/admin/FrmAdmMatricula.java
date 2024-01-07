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
            modelo.setMatriculas(matriculaControl.shellsort(t, cbxCriterioOrden.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al ordenar " + e.getMessage() + "");
        }
        tblMatricula.setModel(modelo);
        tblMatricula.updateUI();
    }

    private void buscar() {
        try {
            modelo.setMatriculas(matriculaControl.busquedaLineal(txtBusqueda.getText(), cbxCriterioBusqueda.getSelectedItem().toString().toLowerCase()));
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatricula = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        cbxCriterioOrden = new javax.swing.JComboBox<>();
        cbxCriterioBusqueda = new javax.swing.JComboBox<>();
        txtNumeroMatricula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxCursa = new javax.swing.JComboBox<>();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxEstudiantes = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnTipoOrden = new javax.swing.JRadioButton();
        btnOrdenar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 460, 200));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 220, -1));

        cbxCriterioOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "ID" }));
        jPanel1.add(cbxCriterioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 120, -1));

        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FechaRegistro", "EstadoMatricula", "ID" }));
        jPanel1.add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 220, -1));
        jPanel1.add(txtNumeroMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 60, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Numero:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, -1, 20));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTE", "APROVADA", "REPROBADA" }));
        jPanel1.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 160, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Estado:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 20));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Cursa:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 20));

        cbxCursa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxCursa, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 160, -1));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 290, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Estudiante");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 20));

        cbxEstudiantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 260, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 800, 10));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ADMINISTRAR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MATRÍCULAS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/HEADER.jpg"))); // NOI18N
        HEADER.setText("jLabel7");
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 800, 170));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnGuardar.setText("    GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 140, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconModificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 140, -1));

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconBorrar.png"))); // NOI18N
        btnBorrar.setText("DAR DE BAJA");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, 140, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
        btnSalir.setText("       SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 130, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconCancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 130, -1));

        btnTipoOrden.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTipoOrden.setText("Descendente");
        btnTipoOrden.setContentAreaFilled(false);
        jPanel1.add(btnTipoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 100, -1));

        btnOrdenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconOrdenar.png"))); // NOI18N
        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("MATRÍCULAS REGISTRADAS EN EL SISTEMA:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            guardar();
        } catch (Exception e) {
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

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        try {
            limpiar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

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
    private javax.swing.JLabel HEADER;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMatricula;
    private javax.swing.JTextField txtBusqueda;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNumeroMatricula;
    // End of variables declaration//GEN-END:variables
}
