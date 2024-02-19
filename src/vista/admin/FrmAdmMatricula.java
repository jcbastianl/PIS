/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.admin;

import controlador.clases.CicloControl;
import controlador.clases.CursaControl;
import controlador.clases.EstudianteControl;
import controlador.clases.MatriculaControl;
import controlador.clases.PeriodoAcademicoControl;
import controlador.dao.AdaptadorDao;
import controlador.ed.listas.ListaEnlazada;
import controlador.utiles.Utiles;
import javax.swing.JOptionPane;
import modelo.Matricula;
import modelo.PeriodoAcademico;
import vista.modeloTablas.CursaModeloTabla;
import vista.modeloTablas.MatriculaModeloTabla;
import vista.modeloTablas.PeriodoAcademicoModeloTabla;
import vista.utiles.UtilVista;

/**
 *
 * @author mrbingus
 */
public class FrmAdmMatricula extends javax.swing.JFrame {

    MatriculaControl matriculaControl = new MatriculaControl();
    MatriculaModeloTabla modelo = new MatriculaModeloTabla();
    PeriodoAcademicoControl periodoControl = new PeriodoAcademicoControl();
    PeriodoAcademicoModeloTabla modeloPeriodo = new PeriodoAcademicoModeloTabla();
    CicloControl cicloControl = new CicloControl();
    CursaControl cursaControl = new CursaControl();
    CursaModeloTabla modeloCursa = new CursaModeloTabla();
    EstudianteControl estudianteControl = new EstudianteControl();

    private Boolean verificar() {
        return (!(txtCodigo.getText().trim().isEmpty())
                && !(txtFecha.getDate() == null)
                && !(cbxCiclo.getSelectedIndex() == -1)
                && !(cbxEstado.getSelectedIndex() == -1)
                && !(cbxEstudiantes.getSelectedIndex() == -1)
                && !(cbxPeriodos.getSelectedIndex() == -1));
    }

    private void cargarTabla() {
    try {
        // Obtener la lista de matrículas
        ListaEnlazada<Matricula> listaMatriculas = matriculaControl.getListaMatriculas();

        // Establecer la lista de matrículas en el modelo de la tabla
        modelo.setMatriculas(listaMatriculas);

        // Establecer el modelo de la tabla y actualizar la interfaz de usuario
        tblMatricula.setModel(modelo);
        tblMatricula.updateUI();
    } catch (Exception e) {
        // Manejar cualquier excepción que pueda ocurrir al cargar los datos
        JOptionPane.showMessageDialog(null, "Error al cargar las matrículas: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void cargarCombos() {
        try {
            UtilVista.cargarcomboBoxEstudiante(cbxEstudiantes);
            UtilVista.cargarcomboBoxCiclos(cbxCiclo);
            UtilVista.cargarcomboBoxPeriodos(cbxPeriodos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarCursasCiclo() {
        try {

            cicloControl.setCiclo(cicloControl.getCiclos().getInfo(cbxCiclo.getSelectedIndex()));
            modeloCursa.setListaCursos(Utiles.identificarCursas(cicloControl.getCiclo().getId()));
            tblCursasCiclo.setModel(modeloCursa);
            tblCursasCiclo.updateUI();
            
        } catch (Exception e) {
        }
    }

    private void limpiar() {
        
        txtFecha.setDate(null);
        txtCodigo.setText("");
        cbxCiclo.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
        cbxEstudiantes.setSelectedIndex(-1);
        cbxPeriodos.setSelectedIndex(-1);

        cargarCursasCiclo();
        cargarTabla();
        cargarCombos();
        tblMatricula.clearSelection();
        
    }

   
   private void guardar(Integer indexCursa, Integer indexEstudiante) {
    if (verificar()) {
        try {
            Matricula matricula = new Matricula();

            
            matricula.setId_cursa(indexCursa);
            matricula.setId_estudiante(indexEstudiante);
            matricula.setEstadoMatricula(Utiles.identificarEstado(cbxEstado.getSelectedIndex()));
            matricula.setId_PeriodoAcademico(cbxPeriodos.getSelectedIndex());
            matricula.setFechaRegistro(txtFecha.getDate());
            matricula.setCodigo(Integer.valueOf(txtCodigo.getText()));

            
            AdaptadorDao<Matricula> adaptadorDao = new AdaptadorDao<>(Matricula.class);
            Integer idGenerado = adaptadorDao.guardar(matricula);

            
            if (idGenerado != -1) {
                JOptionPane.showMessageDialog(null, "Matrícula guardada exitosamente");
                limpiar(); 
                cargarTabla(); 
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la matrícula");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Completa todos los campos requeridos para la matrícula");
    }
}




    private void modificar() throws Exception {

        matriculaControl.setMatricula(matriculaControl.getListaMatriculas().getInfo(tblMatricula.getSelectedRow()));
        if (verificar()) {
            matriculaControl.getMatricula().setEstadoMatricula(Utiles.identificarEstado(cbxEstado.getSelectedIndex()));
            matriculaControl.getMatricula().setFechaRegistro(txtFecha.getDate());
            matriculaControl.getMatricula().setCodigo(Integer.parseInt(txtCodigo.getText()));

            try {
                Integer indexMatricula = Utiles.encontrarPosicion("matricula", modelo.getMatriculas().getInfo(tblMatricula.getSelectedRow()).getId());

                if (matriculaControl.modificar(matriculaControl.getMatricula())) {
                    JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar");
                }
            } catch (Exception e) {
            }
            matriculaControl.setMatricula(null);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Llena todo los campos");

        }
    }

    public void borrar() {

        if (tblMatricula.getSelectedRow() > -1) {
            try {
                Integer indexMatricula = Utiles.encontrarPosicion("matricula", modelo.getMatriculas().getInfo(tblMatricula.getSelectedRow()).getId());
                matriculaControl.setMatricula(matriculaControl.getListaMatriculas().getInfo(indexMatricula));
                matriculaControl.getMatricula().setEstadoMatricula(false);

                if (matriculaControl.merge(matriculaControl.getMatricula(), tblMatricula.getSelectedRow())) {
                    limpiar();
                    indexMatricula = null;
                    JOptionPane.showMessageDialog(null, "Se dio de baja al elemento");
                } else {
                    JOptionPane.showMessageDialog(null, "No se alteraron las matriculas");
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

    private void guardarPeriodoAcademico() {
    if (!txtyearFin.getText().trim().isEmpty()
            && !txtyearInicio.getText().trim().isEmpty()) {
        try {
            // Crear un nuevo objeto PeriodoAcademico
            PeriodoAcademico periodoAcademico = new PeriodoAcademico();

            // Establecer las propiedades del periodo académico
            periodoAcademico.setMesFin(cbxMesFin.getSelectedItem().toString());
            periodoAcademico.setMesInicio(cbxMesInicio.getSelectedItem().toString());
            periodoAcademico.setYearInicio(txtyearInicio.getText());
            periodoAcademico.setYearFin(txtyearFin.getText());

            // Establecer la modalidad del periodo académico
            if (btnIsVirtual.isSelected()) {
                periodoAcademico.setModalidad("VIRTUAL");
            } else {
                periodoAcademico.setModalidad("PRESENCIAL");
            }

            // Guardar el periodo académico utilizando el AdaptadorDao
            AdaptadorDao<PeriodoAcademico> adaptadorDao = new AdaptadorDao<>(PeriodoAcademico.class);
            Integer idGenerado = adaptadorDao.guardar(periodoAcademico);

            // Verificar si se guardó correctamente
            if (idGenerado != -1) {
                JOptionPane.showMessageDialog(null, "Se guardó el periodo académico correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el periodo académico");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el periodo académico: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "No hay suficiente información para crear el periodo académico");
    }
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
        txtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxCiclo = new javax.swing.JComboBox<>();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxEstudiantes = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HEADER = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnTipoOrden = new javax.swing.JRadioButton();
        btnOrdenar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbxPeriodos = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardarPeriodo = new javax.swing.JButton();
        txtyearFin = new javax.swing.JTextField();
        cbxMesFin = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxMesInicio = new javax.swing.JComboBox<>();
        txtyearInicio = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPeriodos = new javax.swing.JTable();
        btnIsVirtual = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCursasCiclo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 700, 230));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });
        jPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, 220, -1));

        cbxCriterioOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "ID" }));
        jPanel1.add(cbxCriterioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 540, 120, -1));

        cbxCriterioBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FechaRegistro", "EstadoMatricula", "ID" }));
        jPanel1.add(cbxCriterioBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 220, -1));
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 140, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Periodo Academico:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, 20));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "APROBADO", "CANCELADO" }));
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 220, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Estado:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, 20));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Ciclo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 20));

        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCicloActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 220, -1));
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 220, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Fecha:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Estudiante");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, 20));

        cbxEstudiantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 220, -1));

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
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MATRÍCULAS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        HEADER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/REDHEADER.png"))); // NOI18N
        HEADER.setText("jLabel7");
        HEADER.setAutoscrolls(true);
        HEADER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(HEADER, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1280, 170));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        btnTipoOrden.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTipoOrden.setText("Descendente");
        btnTipoOrden.setContentAreaFilled(false);
        jPanel1.add(btnTipoOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 540, 100, -1));

        btnOrdenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconOrdenar.png"))); // NOI18N
        btnOrdenar.setText("ORDENAR");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 580, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("MATRÍCULAS REGISTRADAS EN EL SISTEMA:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/icons8-shuffle-20.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 20, 20));

        cbxPeriodos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxPeriodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 392, 310, 30));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("INGRESO DE DATOS:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Codigo");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnGuardarPeriodo.setText("GUARDAR PERIODO ACADEMICO");
        btnGuardarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPeriodoActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));
        jPanel3.add(txtyearFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 70, -1));

        cbxMesFin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        jPanel3.add(cbxMesFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("MES");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("AÑO");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("FIN");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        cbxMesInicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        jPanel3.add(cbxMesInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));
        jPanel3.add(txtyearInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 70, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("AÑO");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, 20));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("MES");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 20));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("INICIO");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("NUEVO PERIODO ACADEMICO");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        tblPeriodos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPeriodos);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 12, 250, 240));

        btnIsVirtual.setText("Virtual");
        btnIsVirtual.setContentAreaFilled(false);
        jPanel3.add(btnIsVirtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("AÑO");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 530, 270));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconSalir.png"))); // NOI18N
        btnSalir.setText("       SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 140, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconCancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 140, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/IconGuardar.png"))); // NOI18N
        btnGuardar.setText("    GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 140, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconModificar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 140, -1));

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/recursos/iconBorrar.png"))); // NOI18N
        btnBorrar.setText("DAR DE BAJA");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 140, -1));

        tblCursasCiclo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblCursasCiclo);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 180, 240));

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
            txtCodigo.setText(matriculaControl.getMatricula().getCodigo().toString());

            cbxCiclo.setSelectedIndex(cursaControl.getListaCursas().getInfo(matriculaControl.getMatricula().getCursa()).getCiclo());
            cbxEstudiantes.setSelectedIndex(matriculaControl.getMatricula().getEstudiante());
            cbxEstado.setSelectedIndex(Utiles.traducirEstadoIndice(matriculaControl.getMatricula().getEstadoMatricula()));
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
        for (int i = 0; i < modeloCursa.getListaCursos().getLength(); i++) {
            guardar(Utiles.encontraridCursa(i), Utiles.encontraridEstudiante(cbxEstudiantes.getSelectedIndex()));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    limpiar();
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //txtCodigo.setText(Utiles.generarCodigoAsignatura(txt, ERROR));


    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGuardarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPeriodoActionPerformed
        // TODO add your handling code here:
        try {
            guardarPeriodoAcademico();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGuardarPeriodoActionPerformed

    private void cbxCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCicloActionPerformed
        // TODO add your handling code here:
        cargarCursasCiclo();
    }//GEN-LAST:event_cbxCicloActionPerformed

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoActionPerformed

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
    private javax.swing.JButton btnGuardarPeriodo;
    private javax.swing.JCheckBox btnIsVirtual;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JRadioButton btnTipoOrden;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JComboBox<String> cbxCriterioBusqueda;
    private javax.swing.JComboBox<String> cbxCriterioOrden;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxEstudiantes;
    private javax.swing.JComboBox<String> cbxMesFin;
    private javax.swing.JComboBox<String> cbxMesInicio;
    private javax.swing.JComboBox<String> cbxPeriodos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCursasCiclo;
    private javax.swing.JTable tblMatricula;
    private javax.swing.JTable tblPeriodos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigo;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtyearFin;
    private javax.swing.JTextField txtyearInicio;
    // End of variables declaration//GEN-END:variables
}
