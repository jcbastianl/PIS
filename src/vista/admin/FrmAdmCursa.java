/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;

import controlador.clases.AsignaturaControl;
import controlador.clases.CicloControl;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import modelo.Asignatura;
import modelo.Ciclo;
import modelo.Cursa;
import modelo.Docente;
import vista.modeloTablas.CicloModeloTabla;
import vista.modeloTablas.CursaModeloTabla;
import vista.utiles.UtilVista;

/**
 *
 * @author mrbingus
 */
public class FrmAdmCursa extends javax.swing.JFrame {

    private CursaControl cursaControl = new CursaControl();
    private AsignaturaControl asignaturaControl = new AsignaturaControl();
    private DocenteControl docenteControl = new DocenteControl();
    private CicloControl cicloControl = new CicloControl();
    private CursaModeloTabla modelo = new CursaModeloTabla();
    private CicloModeloTabla modeloCiclo = new CicloModeloTabla();
    Integer posicion;

    public Boolean verificar() {
        return (txtFechaFin.getDate() != null
                && txtFechaInicio.getDate() != null
                && !(cbxDocente.getSelectedIndex() == -1)
                && !(cbxAsignatura.getSelectedIndex() == -1)
                && !(cbxCiclo.getSelectedIndex() == -1));
    }

 private void cargarTablas() {
    try {
        // Obtener la lista de cursas
        ListaEnlazada<Cursa> listaCursas = cursaControl.getListaCursas();
        ListaEnlazada<Asignatura> listaAsignatura = asignaturaControl.getListaAsignaturas();
        ListaEnlazada<Docente> listaDocente = docenteControl.getListaDocentes();
        ListaEnlazada<Ciclo> listaCiclos = cicloControl.getCiclos();

        // Establecer la lista de cursas en el modelo de la tabla
        modelo.setListaCursos(listaCursas);
        modelo.setListaAsignaturas(listaAsignatura);
        modelo.setListaDocentes(listaDocente);
        modelo.setListaCiclos(listaCiclos);
        // Establecer el modelo de la tabla y actualizar la interfaz de usuario
        tblCursa.setModel(modelo);
        tblCursa.updateUI();
    } catch (Exception e) {
        // Manejar cualquier excepción que pueda ocurrir al cargar los datos
        JOptionPane.showMessageDialog(null, "Error al cargar las cursas: " + e.getMessage());
        e.printStackTrace();
    }
}


    public void cargarCombo() {
        try {
            UtilVista.cargarcomboBoxDocente(cbxDocente);
            UtilVista.cargarcomboBoxAsignatura(cbxAsignatura);
            UtilVista.cargarcomboBoxCiclos(cbxCiclo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void limpiar() {
        txtFechaFin.setDate(null);
        txtFechaInicio.setDate(null);
        txtLetra.setText("");
        txtCiclo.setText("");
        cbxDocente.setSelectedIndex(-1);
        cbxCiclo.setSelectedIndex(-1);
        cbxAsignatura.setSelectedIndex(-1);
        cargarTablas();
        cargarCombo();
        cbxCiclo.setEnabled(true);
        tblCursa.clearSelection();
    }

  private void guardar() {
    if (verificar()) {
        try {
       
            Cursa cursa = new Cursa();
            cursa.setFechaFin(txtFechaFin.getDate());
            cursa.setFechaInicio(txtFechaInicio.getDate());

            Docente docenteSeleccionado = (Docente) cbxDocente.getSelectedItem();
            int idDocente = docenteSeleccionado.getId();

          
            cursa.setDocenteId(idDocente);

            
            Asignatura asignaturaSeleccionada = (Asignatura) cbxAsignatura.getSelectedItem();
            int idAsignatura = asignaturaSeleccionada.getId();

            Ciclo cicloSeleccionado = (Ciclo) cbxCiclo.getSelectedItem();
            int idCiclo = cicloSeleccionado.getId();

            
            cursa.setAsignaturaId(idAsignatura);
            cursa.setCicloId(idCiclo);

            
            AdaptadorDao<Cursa> adaptadorDao = new AdaptadorDao<>(Cursa.class);

            
            Integer idGenerado = adaptadorDao.guardar(cursa);

            if (idGenerado != -1) {
                limpiar();
                JOptionPane.showMessageDialog(null, "Se guardó correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar la cursa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Completa los campos requeridos para la cursa");
    }
}







    public void modificar() {
    if (verificar()) {
        try {
            Cursa cursa = new Cursa();
            cursa.setFechaInicio(txtFechaInicio.getDate());
            cursa.setFechaFin(txtFechaFin.getDate());
            
            Asignatura asignaturaSeleccionada = (Asignatura) cbxAsignatura.getSelectedItem();
            cursa.setAsignaturaId(asignaturaSeleccionada.getId());
            
            Docente docenteSeleccionado = (Docente) cbxDocente.getSelectedItem();
            cursa.setDocenteId(docenteSeleccionado.getId());
            
            Ciclo cicloSeleccionado = (Ciclo) cbxCiclo.getSelectedItem();
            cursa.setCicloId(cicloSeleccionado.getId());

            // Suponiendo que cursaControl es una instancia de la clase que maneja la lógica de negocio de Cursa
            cursaControl.modificar(cursa);

            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar la cursa: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos");
    }

    limpiar();
}


    public void borrar() {
        if (tblCursa.getSelectedRow() > -1) {
            if (cursaControl.remove(tblCursa.getSelectedRow())) {
                JOptionPane.showMessageDialog(null, "Se borro el elemento");
            } else {
                JOptionPane.showMessageDialog(null, "No se borro el elemento");
            }
            limpiar();
            cicloControl.setCiclo(null);
            cursaControl.setCursa(null);
        } else {

            JOptionPane.showConfirmDialog(null, "Selecciona un elemento a borrar");
        }

    }

    public void buscar() {
        try {
            modelo.setListaCursos(cursaControl.busquedaLineal(txtBuscar.getText(), cbxCriterioBusqueda.getSelectedItem().toString().toLowerCase()));
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage() + "");
        }
        tblCursa.setModel(modelo);
        tblCursa.updateUI();
    }

    private void ordenar() {
        int tipo = btnTipoOrden.isSelected() ? 1 : 0; // Determinar el tipo de orden
        String criterioOrden = btnTipoOrden.getActionCommand().toString().toLowerCase(); // Obtener el criterio de orden

        try {
            // Obtener la lista de cursos ordenada utilizando el método shellsort de CursaControl
            ListaEnlazada<Cursa> listaOrdenada = cursaControl.shellsort(tipo, criterioOrden, cursaControl.getListaCursas());

            // Actualizar el modelo de la tabla con la lista ordenada
            modelo.setListaCursos(listaOrdenada);
            tblCursa.setModel(modelo);
            tblCursa.updateUI();
        } catch (Exception e) {
            System.out.println("Error al ordenar: " + e.getMessage());
        }
    }

    private void guardarCiclo() {
        if (!(txtLetra.getText().trim().isEmpty())
                && !(txtCiclo.getText().trim().isEmpty())
                && (txtLetra.getText().length() == 1)) {
            Ciclo ciclo = new Ciclo();
            ciclo.setCiclo(Integer.parseInt(txtCiclo.getText()));
            ciclo.setParalelo(txtLetra.getText());

            try {
                // Guardar en la base de datos utilizando AdaptadorDao
                AdaptadorDao<Ciclo> adaptadorDao = new AdaptadorDao<>(Ciclo.class);
                Integer idGenerado = adaptadorDao.guardar(ciclo);

                if (idGenerado != -1) {
                    JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                    limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el ciclo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay suficientes datos para un nuevo Ciclo");
        }
        cicloControl.setCiclo(null);
    }

    /**
     * Creates new form FrmAdmCursa
     */
    public FrmAdmCursa() {
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

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursa = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbxDocente = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        cbxCriterioBusqueda = new javax.swing.JComboBox<>();
        cbxCriterioOrdenar = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        btnTipoOrden = new javax.swing.JRadioButton();
        cbxAsignatura = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFechaFin = new com.toedter.calendar.JDateChooser();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbxCiclo = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCiclo = new javax.swing.JTextField();
        txtLetra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCiclos = new javax.swing.JTable();

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel6.setText("Nombres:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCursa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblCursa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCursa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCursaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCursa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 660, 260));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("CURSOS REGISTRADOS EN EL SISTEMA:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, 20));

        cbxDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDocenteActionPerformed(evt);
            }
        });
        jPanel1.add(cbxDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 220, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnGuardar.setText("    GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 140, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconModificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 140, -1));

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconBorrar.png"))); // NOI18N
        btnBorrar.setText("DAR DE BAJA");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 140, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconCancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 140, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
        btnSalir.setText("       SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 140, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Fecha Fin:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

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

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADMINISTRAR");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CURSOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/REDHEADER.png"))); // NOI18N
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1280, 170));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, 220, -1));

        cbxCriterioBusqueda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciclo", "Asignatura" }));
        jPanel1.add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 440, 220, -1));

        cbxCriterioOrdenar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxCriterioOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciclo", "Asignatura" }));
        cbxCriterioOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriterioOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCriterioOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 510, 120, -1));

        btnOrdenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconOrdenar.png"))); // NOI18N
        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 550, -1, -1));

        btnTipoOrden.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTipoOrden.setText("Descendente");
        btnTipoOrden.setContentAreaFilled(false);
        jPanel1.add(btnTipoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, 100, -1));

        cbxAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 220, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Docente asignado:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 20));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));
        jPanel1.add(txtFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 220, -1));
        jPanel1.add(txtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 220, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Asignatura:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 20));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Ciclo:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, 20));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Fecha Inicio:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 20));

        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 220, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        jButton1.setText("GUARDAR UN CICLO");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Ciclo:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));
        jPanel3.add(txtCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 180, -1));
        jPanel3.add(txtLetra, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 180, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Paralelo");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("CREAR UN CICLO");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        tblCiclos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblCiclos);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 340, 190));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 650, 230));

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

    private void tblCursaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCursaMouseClicked
        // TODO add your handling code here:
        try {
            cursaControl.setCursa(modelo.getListaCursos().getInfo(tblCursa.getSelectedRow()));
            txtFechaFin.setDate(cursaControl.getCursa().getFechaFin());
            txtFechaInicio.setDate(cursaControl.getCursa().getFechaInicio());
            cbxCiclo.setSelectedIndex(cursaControl.getCursa().getCiclo().getId());
            cbxAsignatura.setSelectedIndex(cursaControl.getCursa().getAsignatura().getId() - 1);
            cbxDocente.setSelectedIndex(Utiles.encontrarPosicion("docente", cursaControl.getCursa().getDocente().getId()));
            cbxCiclo.setEnabled(false);
            //System.out.println(Utiles.encontrarPosicion("cursa", modelo.getListaCursos().getInfo(tblCursa.getSelectedRow()).getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }//GEN-LAST:event_tblCursaMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        try {
            buscar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            guardarCiclo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxCriterioOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriterioOrdenarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriterioOrdenarActionPerformed

    private void cbxDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocenteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdmCursa().setVisible(true);
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
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JComboBox<String> cbxCriterioBusqueda;
    private javax.swing.JComboBox<String> cbxCriterioOrdenar;
    private javax.swing.JComboBox<String> cbxDocente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCiclos;
    private javax.swing.JTable tblCursa;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCiclo;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    private javax.swing.JTextField txtLetra;
    // End of variables declaration//GEN-END:variables
}
