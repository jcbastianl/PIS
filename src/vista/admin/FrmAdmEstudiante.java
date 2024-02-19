/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;


import controlador.TDA.listas.Exception.EmptyException;
import controlador.clases.CuentaControl;
import controlador.clases.EstudianteControl;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import modelo.Cuenta;
import modelo.Estudiante;

import vista.utiles.UtilVista;

import vista.modeloTablas.EstudianteModeloTabla;

/**
 *
 * @author mrbingus
 */
public class FrmAdmEstudiante extends javax.swing.JFrame {

    private EstudianteControl estudianteControl = new EstudianteControl();
    private EstudianteModeloTabla modelo = new EstudianteModeloTabla();
    private CuentaControl cuentaControl = new CuentaControl();
    

    private Boolean verificar() {
        return (!(txtApellido.getText().trim().isEmpty())
                && !(txtNombre.getText().trim().isEmpty())
                && !txtColegio.getText().trim().isEmpty()
                && !txtProvincia.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !(txtDni.getText().trim().isEmpty()));
    }

    private Boolean verificarDatosCuenta() {
        return (!(txtCorreo.getText().trim().isEmpty())
                && !(txtClaveUno.getText().trim().isEmpty())
                && !(txtClaveDos.getText().trim().isEmpty()));
    }

 private void cargarTabla() {
    try {
    
        ListaEnlazada<Estudiante> listaEstudiantes = estudianteControl.getListaEstudiantes();

       
        modelo.setEstudiantes(listaEstudiantes);

        
        tblEstudiante.setModel(modelo);
        tblEstudiante.updateUI();
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "Error al cargar los estudiantes: " + e.getMessage());
        e.printStackTrace();
    }
}



    private void limpiar() {
        txtApellido.setText("");
        txtBusqueda.setText("");
        txtDni.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtColegio.setText("");
        txtProvincia.setText("");
        txtCorreo.setText("");
        txtClaveUno.setText("");
        txtClaveDos.setText("");
        btnEstadoExtranjero.setSelected(false);
        tblEstudiante.clearSelection();
        cargarTabla();
    }

    private void guardar() {
    if (verificar()) {
        try {
            
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(txtNombre.getText());
            estudiante.setApellido(txtApellido.getText());
            estudiante.setDni(txtDni.getText());
            estudiante.setTelefono(txtTelefono.getText());
            estudiante.setColegioProcedencia(txtColegio.getText());
            estudiante.setProvinciaOrigen(txtProvincia.getText());
            estudiante.setRol(1);

            
            AdaptadorDao<Estudiante> adaptadorDaoEstudiante = new AdaptadorDao<>(Estudiante.class);

            
            Integer idGeneradoEstudiante = adaptadorDaoEstudiante.guardar(estudiante);

           
            if (idGeneradoEstudiante != -1) {
                JOptionPane.showMessageDialog(null, "Estudiante guardado exitosamente");

             
                Cuenta cuenta = new Cuenta();
                cuenta.setCorreo(txtCorreo.getText());
                cuenta.setContraseña(txtClaveUno.getText());
                cuenta.setEstado(true);
                cuenta.setId(idGeneradoEstudiante); 

               
                AdaptadorDao<Cuenta> adaptadorDaoCuenta = new AdaptadorDao<>(Cuenta.class);

             
                Integer idGeneradoCuenta = adaptadorDaoCuenta.guardar(cuenta);

                
                if (idGeneradoCuenta != -1) {
                    JOptionPane.showMessageDialog(null, "Cuenta guardada exitosamente");
                    limpiar();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar la cuenta");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el estudiante");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Completa los campos requeridos para el estudiante y la cuenta");
    }
}


   private void modificar() {
    if (verificar()) {
        try {
           
            Integer indiceEstudiante = tblEstudiante.getSelectedRow();

           
            if (indiceEstudiante != -1) {
         
                estudianteControl.getEstudiante().setNombre(txtNombre.getText());
                estudianteControl.getEstudiante().setApellido(txtApellido.getText());
                estudianteControl.getEstudiante().setDni(txtDni.getText());
                estudianteControl.getEstudiante().setColegioProcedencia(txtColegio.getText());
                estudianteControl.getEstudiante().setProvinciaOrigen(txtProvincia.getText());
                estudianteControl.getEstudiante().setTelefono(txtTelefono.getText());

               
                estudianteControl.modificar(estudianteControl.getEstudiante());

               
                cargarTabla();

                JOptionPane.showMessageDialog(null, "Modificación Exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un estudiante para modificar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Rellena todos los campos");
    }
}



    private void borrar() {
    try {
        if (tblEstudiante.getSelectedRow() > -1) {
            Integer indiceEstudiante = tblEstudiante.getSelectedRow();
            Estudiante estudianteSeleccionado = estudianteControl.getListaEstudiantes().getInfo(indiceEstudiante);

            
            if (estudianteControl.remove(indiceEstudiante)) {
                JOptionPane.showMessageDialog(null, "Se borró el estudiante");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo borrar el estudiante");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un estudiante para eliminar");
        }
    } catch (Exception e) {
      
        e.printStackTrace();
    }

 
    limpiar();
}


   private void ordenar() {
    int tipo = btnTipoOrden.isSelected() ? 1 : 0; 
    String criterioOrden = cbxCriterioOrden.getSelectedItem().toString().toLowerCase(); 

    try {
        
        ListaEnlazada<Estudiante> listaOrdenada = estudianteControl.shellsort(tipo, criterioOrden, estudianteControl.getListaEstudiantes());

        
        modelo.setEstudiantes(listaOrdenada);
        tblEstudiante.setModel(modelo);
        tblEstudiante.updateUI();
    } catch (Exception e) {
        System.out.println("Error al ordenar: " + e.getMessage());
    }
}


    private void buscar() {
        try {
            modelo.setEstudiantes(estudianteControl.busquedaBinaria(txtBusqueda.getText(), cbxCriterioBusqueda.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage() + "");
        }
        tblEstudiante.setModel(modelo);
        tblEstudiante.updateUI();
    }

    /**
     * Creates new form frmDocente
     */
    public FrmAdmEstudiante() {
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
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiante = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbxCriterioBusqueda = new javax.swing.JComboBox<>();
        cbxCriterioOrden = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnTipoOrden = new javax.swing.JRadioButton();
        btnOrdenar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtClaveUno = new javax.swing.JTextField();
        txtClaveDos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnEstadoExtranjero = new javax.swing.JRadioButton();
        txtColegio = new javax.swing.JTextField();
        txtProvincia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(465, 570));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMinimumSize(new java.awt.Dimension(455, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(465, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 60, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Provincia:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 70, 20));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 230, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 230, -1));

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 230, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblEstudiante.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEstudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstudianteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstudiante);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 460, 200));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Nombres:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 70, 20));

        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "ID" }));
        cbxCriterioBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioBusquedaActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 220, -1));

        cbxCriterioOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "ID" }));
        jPanel1.add(cbxCriterioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 120, -1));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 220, -1));

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
        jLabel8.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ESTUDIANTES");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ADMINISTRAR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/REDHEADER.png"))); // NOI18N
        HEADER.setText("jLabel7");
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 150));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

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

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("CURSOS REGISTRADOS EN EL SISTEMA:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, 20));

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

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("CUENTA:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Nombre de usuario:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, -1, 20));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 230, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Contraseña (Repetir):");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, 20));

        txtClaveUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveUnoActionPerformed(evt);
            }
        });
        txtClaveUno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveUnoKeyTyped(evt);
            }
        });
        jPanel1.add(txtClaveUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, 230, -1));

        txtClaveDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveDosActionPerformed(evt);
            }
        });
        jPanel1.add(txtClaveDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 230, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 230, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Teléfono:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 20));

        btnEstadoExtranjero.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEstadoExtranjero.setText("Usuario Extranjero");
        btnEstadoExtranjero.setContentAreaFilled(false);
        btnEstadoExtranjero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoExtranjeroActionPerformed(evt);
            }
        });
        jPanel1.add(btnEstadoExtranjero, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));
        jPanel1.add(txtColegio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 220, -1));
        jPanel1.add(txtProvincia, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 220, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("DNI:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 40, 20));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Colegio:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 50, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblEstudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstudianteMouseClicked
        // TODO add your handling code here:
        try {
            Integer indiceEstudiante = Utiles.encontrarPosicion("estudiante", modelo.getEstudiantes().getInfo(tblEstudiante.getSelectedRow()).getId());
            estudianteControl.setEstudiante(estudianteControl.getListaEstudiantes().getInfo(indiceEstudiante));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtApellido.setText(estudianteControl.getEstudiante().getApellido());
        txtNombre.setText(estudianteControl.getEstudiante().getNombre());
        txtDni.setText(estudianteControl.getEstudiante().getDni());
        txtTelefono.setText(estudianteControl.getEstudiante().getTelefono());
        txtProvincia.setText(estudianteControl.getEstudiante().getProvinciaOrigen());
        txtColegio.setText(estudianteControl.getEstudiante().getColegioProcedencia());

    }//GEN-LAST:event_tblEstudianteMouseClicked

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void cbxCriterioBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterioBusquedaActionPerformed

    public void generarUser() {
        txtCorreo.setText(Utiles.crearNombreUser(txtNombre.getText(), txtApellido.getText()));

    }

    public void rellenarClaves() {
        txtClaveUno.setText(txtDni.getText());
        txtClaveDos.setText(txtClaveUno.getText());
    }

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        generarUser();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        try {
            buscar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

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

    private void txtClaveUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveUnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveUnoActionPerformed

    private void txtClaveDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveDosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveDosActionPerformed

    private void btnEstadoExtranjeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoExtranjeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstadoExtranjeroActionPerformed

    private void txtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoKeyPressed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
        generarUser();
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        // TODO add your handling code here:
        rellenarClaves();
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtClaveUnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveUnoKeyTyped
        // TODO add your handling code here:
        txtClaveDos.setText(txtClaveUno.getText());
    }//GEN-LAST:event_txtClaveUnoKeyTyped

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
            java.util.logging.Logger.getLogger(FrmAdmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdmEstudiante().setVisible(true);
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
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JTable tblEstudiante;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtClaveDos;
    private javax.swing.JTextField txtClaveUno;
    private javax.swing.JTextField txtColegio;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProvincia;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
