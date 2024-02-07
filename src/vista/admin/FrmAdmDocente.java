/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;

import controlador.clases.CuentaControl;
import controlador.clases.DocenteControl;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import vista.modeloTablas.DocenteModeloTabla;

/**
 *
 * @author mrbingus
 */
public class FrmAdmDocente extends javax.swing.JFrame {

    private DocenteControl docenteControl = new DocenteControl();
    private DocenteModeloTabla modelo = new DocenteModeloTabla();

    private CuentaControl cuentaControl = new CuentaControl();

    private Boolean verificar() {
        return (!(txtApellido.getText().trim().isEmpty())
                && !(txtNombre.getText().trim().isEmpty())
                && !(txtDni.getText().trim().isEmpty())
                && !(txtTitulo.getText().trim().isEmpty())
                && !(txtCorreo.getText().trim().isEmpty())
                && !(txtClaveUno.getText().trim().isEmpty())
                && !(txtClaveDos.getText().trim().isEmpty())
                && !txtTelefono.getText().trim().isEmpty()
                && !txtPreparacion.getText().trim().isEmpty());
    }

    public void cargarTabla() {
        modelo.setDocentes(docenteControl.getListaDocentes());
        tblDocente.setModel(modelo);
        tblDocente.updateUI();
    }

    private void limpiar() {
        txtApellido.setText("");
        txtBusqueda.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        txtTitulo.setText("");
        txtClaveDos.setText("");
        txtClaveUno.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtPreparacion.setText("");
        tblDocente.clearSelection();
        btnEstadoExtranjero.setSelected(false);
        cargarTabla();
    }

    private void guardar() {
        if (verificar()) {
            docenteControl.getDocente().setNombre(txtNombre.getText());
            docenteControl.getDocente().setApellido(txtApellido.getText());
            docenteControl.getDocente().setDni(txtDni.getText());
            docenteControl.getDocente().setTitulo(txtTitulo.getText());
            docenteControl.getDocente().setRol(0);
            docenteControl.getDocente().setTelefono(txtTelefono.getText());
            docenteControl.getDocente().setPreparacion(txtPreparacion.getText());
            cuentaControl.getCuenta().setCorreo(txtCorreo.getText());
            if (Utiles.compararTextoss(txtClaveUno.getText(), txtClaveDos.getText())) {
                cuentaControl.getCuenta().setContraseña(txtClaveUno.getText());
                cuentaControl.getCuenta().setPersona(docenteControl.getDocente());
                cuentaControl.getCuenta().setEstado(true);
                if (docenteControl.persist()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                    if (cuentaControl.persist()) {
                        JOptionPane.showMessageDialog(null, "Cuenta registrada con exito");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrarse");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Las claves no coinciden");
            }

            docenteControl.setDocente(null);

        } else {
            JOptionPane.showMessageDialog(null, "No dejes campos vacios");
        }
    }

    private void modificar() {
        if (verificar()) {
            docenteControl.getDocente().setNombre(txtNombre.getText());
            docenteControl.getDocente().setApellido(txtApellido.getText());
            docenteControl.getDocente().setDni(txtDni.getText());
            docenteControl.getDocente().setTitulo(txtTitulo.getText());
            docenteControl.getDocente().setTelefono(txtTelefono.getText());
            docenteControl.getDocente().setPreparacion(txtPreparacion.getText());
            try {
                Integer indiceDocente = Utiles.encontrarPosicion("docente", modelo.getDocentes().getInfo(tblDocente.getSelectedRow()).getId());

                if (docenteControl.merge(docenteControl.getDocente(), indiceDocente)) {
                    JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            docenteControl.setDocente(null);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "No dejes campos vacios");

        }
    }

    private void borrar() {
        try {
            Integer indiceDocente = Utiles.encontrarPosicion("docente", modelo.getDocentes().getInfo(tblDocente.getSelectedRow()).getId());

            if (tblDocente.getSelectedRow() > -1) {
                if (docenteControl.remove(indiceDocente)) {
                    JOptionPane.showMessageDialog(null, "Se borro el elemento");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo borrar el elemento");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un elemento para eliminar");
            }
        } catch (Exception e) {
        }
        limpiar();
    }

    private void ordenar() {
        int t = 0;
        if (btnTipoOrden.isSelected()) {
            t = 1;
        }
        try {
            modelo.setDocentes(docenteControl.shellsort(t, cbxCriterioOrden.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al ordenar " + e.getMessage() + "");
        }
        tblDocente.setModel(modelo);
        tblDocente.updateUI();
    }

    private void buscar() {
        try {
            modelo.setDocentes(docenteControl.busquedaBinaria(txtBusqueda.getText(), cbxCriterioBusqueda.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage() + "");
        }
        tblDocente.setModel(modelo);
        tblDocente.updateUI();
    }

    /**
     * Creates new form frmDocente
     */
    public FrmAdmDocente() {
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbxCriterioBusqueda = new javax.swing.JComboBox<>();
        cbxCriterioOrden = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnOrdenar = new javax.swing.JButton();
        btnTipoOrden = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtClaveUno = new javax.swing.JTextField();
        txtClaveDos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnEstadoExtranjero = new javax.swing.JRadioButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        txtPreparacion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(465, 570));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMinimumSize(new java.awt.Dimension(455, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("DNI:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 20));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Titulo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 20));
        jPanel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 220, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 220, -1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 220, -1));
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 220, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocenteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDocente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 670, 260));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Preparación:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, -1, 20));

        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Titulo", "DNI", "ID" }));
        jPanel1.add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, 220, -1));

        cbxCriterioOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "Titulo", "DNI", "ID" }));
        jPanel1.add(cbxCriterioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 120, -1));

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, 220, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1280, 10));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ADMINISTRAR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("DOCENTES");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/REDHEADER.png"))); // NOI18N
        HEADER.setText("jLabel7");
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 150));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("DOCENTES REGISTRADOS EN EL SISTEMA:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("CUENTA:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, -1, 20));

        btnOrdenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconOrdenar.png"))); // NOI18N
        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, -1, -1));

        btnTipoOrden.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTipoOrden.setText("Descendente");
        btnTipoOrden.setContentAreaFilled(false);
        jPanel1.add(btnTipoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 490, 100, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Nombre de usuario:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, -1, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Contraseña (Repetir):");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, 20));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 230, -1));

        txtClaveUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveUnoActionPerformed(evt);
            }
        });
        jPanel1.add(txtClaveUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, 230, -1));

        txtClaveDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveDosActionPerformed(evt);
            }
        });
        jPanel1.add(txtClaveDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 230, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 220, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Nombres:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 20));

        btnEstadoExtranjero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEstadoExtranjero.setText("Usuario Extranjero");
        btnEstadoExtranjero.setContentAreaFilled(false);
        btnEstadoExtranjero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoExtranjeroActionPerformed(evt);
            }
        });
        jPanel1.add(btnEstadoExtranjero, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconCancelar.png"))); // NOI18N
        btnCancelar.setText("   CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 350, 140, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
        btnSalir.setText("           SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 400, 140, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnGuardar.setText("    GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, 140, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconModificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 250, 140, -1));

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconBorrar.png"))); // NOI18N
        btnBorrar.setText("DAR DE BAJA");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 300, 140, -1));
        jPanel1.add(txtPreparacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 220, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Teléfono:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocenteMouseClicked
        // TODO add your handling code here:
        try {
            Integer indiceDocente = Utiles.encontrarPosicion("docente", modelo.getDocentes().getInfo(tblDocente.getSelectedRow()).getId());
            docenteControl.setDocente(docenteControl.getListaDocentes().getInfo(indiceDocente));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtApellido.setText(docenteControl.getDocente().getApellido());
        txtNombre.setText(docenteControl.getDocente().getNombre());
        txtDni.setText(docenteControl.getDocente().getDni());
        txtTitulo.setText(docenteControl.getDocente().getTitulo());
        txtPreparacion.setText(docenteControl.getDocente().getPreparacion());

    }//GEN-LAST:event_tblDocenteMouseClicked

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void txtClaveUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveUnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveUnoActionPerformed

    private void txtClaveDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveDosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveDosActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        try {
            buscar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void btnEstadoExtranjeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoExtranjeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstadoExtranjeroActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdmDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HEADER;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JRadioButton btnEstadoExtranjero;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JRadioButton btnTipoOrden;
    private javax.swing.JComboBox<String> cbxCriterioBusqueda;
    private javax.swing.JComboBox<String> cbxCriterioOrden;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtClaveDos;
    private javax.swing.JTextField txtClaveUno;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPreparacion;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
