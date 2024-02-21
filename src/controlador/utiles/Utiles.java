/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.utiles;

import controlador.TDA.listas.DynamicList;
import controlador.clases.AsignaturaControl;
import controlador.clases.AsistenciaControl;
import controlador.clases.CicloControl;
import controlador.clases.ClaseDictadaControl;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstudianteControl;
import controlador.clases.JustificativoControl;
import controlador.clases.MatriculaControl;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.listas.NodoLista;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import modelo.Asistencia;
import modelo.Ciclo;
import modelo.ClaseDictada;
import modelo.Cursa;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Justificativo;

import modelo.Matricula;

public class Utiles {


    //CMETODO PARA VALIDAD CEDULAS 
    public static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;

    }

    //METODO GETFIELD REALIZADO EN CLASE
    public static Field getField(Class clazz, String atribute) {
        Field field = null;
        //Field[] fileds = clazz.getFields();
        for (Field f : clazz.getSuperclass().getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equalsIgnoreCase(atribute)) {
                field = f;
                break;
            }
        }
        return field;
    }

//    public static Integer encontrarPosicion(String tipoObj, Integer id) {
//
//        try {
//
//            switch (tipoObj) {
//                case "docente":
//                    DocenteControl dc = new DocenteControl();
//                    for (int i = 0; i < dc.all().getLenght(); i++) {
//                        if (Objects.equals(dc.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                    break;
//                case "matricula":
//                    MatriculaControl cc = new MatriculaControl();
//                    for (int i = 0; i < cc.all().getLenght(); i++) {
//                        if (Objects.equals(cc.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                case "estudiante":
//                    EstudianteControl ec = new EstudianteControl();
//                    for (int i = 0; i < ec.all().getLenght(); i++) {
//                        if (Objects.equals(ec.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                case "cursa":
//                    CursaControl curc = new CursaControl();
//                    for (int i = 0; i < curc.all().getLenght(); i++) {
//                        if (Objects.equals(curc.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                case "asignatura":
//                    AsignaturaControl ac = new AsignaturaControl();
//                    for (int i = 0; i < ac.all().getLenght(); i++) {
//                        if (Objects.equals(ac.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                    break;
//                case "ciclo":
//                    CicloControl cic = new CicloControl();
//                    for (int i = 0; i < cic.all().getLenght(); i++) {
//                        if (Objects.equals(cic.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                    break;
//                case "clasedictada":
//                    ClaseDictadaControl cdc = new ClaseDictadaControl();
//                    for (int i = 0; i < cdc.all().getLenght(); i++) {
//                        if (Objects.equals(cdc.all().getInfo(i).getId(), id)) {
//                            return i;
//                        }
//                    }
//                    break;
//                default:
//                    throw new AssertionError();
//            }
//            return -1;
//        } catch (Exception e) {
//            return -1;
//        }
//    }
    public static Integer encontrarPosicion(String tipoObj, Integer id) {
        try {
            switch (tipoObj) {
                case "docente":
                    return encontrarPosicion(DocenteControl.class, id);
                case "matricula":
                    return encontrarPosicion(MatriculaControl.class, id);
                case "estudiante":
                    return encontrarPosicion(EstudianteControl.class, id);
                case "cursa":
                    return encontrarPosicion(CursaControl.class, id);
                case "asignatura":
                    return encontrarPosicion(AsignaturaControl.class, id);
                case "ciclo":
                    return encontrarPosicion(CicloControl.class, id);
                case "clasedictada":
                    return encontrarPosicion(ClaseDictadaControl.class, id);
                case "justificativo":
                    return encontrarPosicion(JustificativoControl.class, id);
                case "asistencia":
                    return encontrarPosicion(AsistenciaControl.class, id);
                default:
                    throw new IllegalArgumentException("Tipo de objeto desconocido: " + tipoObj);
            }
        } catch (Exception e) {
            return -1;
        }
    }

    private static <T> Integer encontrarPosicion(Class<T> controlClass, Integer id) throws Exception {
        T control = controlClass.getDeclaredConstructor().newInstance();
        DynamicList<T> list = (DynamicList<T>) control.getClass().getMethod("all").invoke(control);
        for (int i = 0; i < list.getLenght(); i++) {
            Object obj = list.getInfo(i);
            if (obj.getClass().getMethod("getId").invoke(obj).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    //METODO PARA DEVOLVER UN STRING DE UN DECIMAL CON UN FORMATO APTO PARA COMPARAR COMO STRING
    public static String decimalFormato(Double d) {
        try {

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(d);
        } catch (IllegalArgumentException e) {

            System.err.println("Error: Formato inválido.");
            return "";
        }
    }

    //APLICAR UN FORMATO A UN TIPO DATE PARA REPRESENTARSE GRAFICAMENTE
    public static String formaterarFecha(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date);
    }

    //VERIFICAR QUE DOS TEXTOS SEAN IDENTICOS
    public static boolean compararTextoss(String text1, String text2) {
        return (text1.equals(text2));

    }

    //GENERAR UN CODIGO EN BASE A UN TEXTO Y UN NUMERO
    public static String generarCodigoAsignatura(String texto, int numero) {
        StringBuilder codigo = new StringBuilder();

        // Separar el texto en palabras
        String[] palabras = texto.split("\\s+");

        // Si el texto tiene varias palabras
        if (palabras.length > 1) {
            for (String palabra : palabras) {
                // Tomar la primera letra de cada palabra hasta alcanzar una longitud de 5
                if (codigo.length() < 5) {
                    codigo.append(Character.toUpperCase(palabra.charAt(0)));
                } else {
                    break;
                }
            }
        } else {
            // Si el texto tiene solo una palabra
            codigo.append(texto.substring(0, Math.min(texto.length(), 5)).toUpperCase());
        }

        // Agregar el número al código
        codigo.append(numero);

        return codigo.toString();
    }

    //GENERAR USUARIO
    public static String crearNombreUser(String nombre, String apellido) {
        return nombre.toLowerCase() + "" + apellido.toLowerCase();
    }

    public static Integer encontrarIndexSegunLista(DynamicList<Matricula> lista, Integer idBuscar) throws Exception {
        for (int i = 0; i < lista.getLenght(); i++) {
            if (Objects.equals(lista.getInfo(i).getId(), idBuscar)) {
                return i;
            }
        }
        return null;
    }

    public static Boolean identificarEstado(Integer i) {
        if (i == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String traducirEstadoString(Boolean i) {
        if (i) {
            return "APROBADO";
        } else {
            return "CANCELADO";
        }
    }

    public static String traducirEstadoAsistenciaString(Boolean i) {
        if (i) {
            return "PRESENTE";
        } else {
            return "AUSENTE";
        }
    }

    public static Integer traducirEstadoIndice(Boolean i) {
        if (i) {
            return 0;
        } else {
            return 1;
        }
    }

    public static ListaEnlazada<Cursa> identificarCursas(int u) throws Exception {
        CursaControl cc = new CursaControl();
        ListaEnlazada<Cursa> aux = new ListaEnlazada<>();
        for (int i = 0; i < cc.listar().getLength(); i++) {
            if (cc.listar().getInfo(i).getCicloId() == u) {
                aux.insertar(cc.listar().getInfo(i));
            }
        }
        return aux;
    }

    public static Integer encontraridDocente(Integer u) throws Exception {
        return new DocenteControl().getListaDocentes().getInfo(u).getId();
    }

    public static Integer encontraridCiclo(Integer u) throws Exception {
        return new CicloControl().getCiclos().getInfo(u).getId();
    }

    public static Integer encontraridEstudiante(Integer u) throws Exception {
        return new EstudianteControl().getListaEstudiantes().getInfo(u).getId();
    }

    public static Integer encontraridCursa(Integer u) throws Exception {
        return new CursaControl().getListaCursas().getInfo(u).getId();
    }

    public static Integer encontraridClase(Integer u) throws Exception {
        return new ClaseDictadaControl().getListaClases().getInfo(u).getId();
    }

    public static ListaEnlazada<Cursa> recuperarCursasDocente(Integer id) throws Exception {
        CursaControl cc = new CursaControl();
        ListaEnlazada<Cursa> aux = new ListaEnlazada<>();
        for (int i = 0; i < cc.listar().getLength(); i++) {
            if (cc.listar().getInfo(i).getDocente().equals(id)) {
                aux.insertar(cc.listar().getInfo(i));
            }
        }
        return aux;
    }

    public static ListaEnlazada<ClaseDictada> recuperarClasesCursa(Integer id) throws Exception {
        ClaseDictadaControl cd = new ClaseDictadaControl();
        ListaEnlazada<ClaseDictada> aux = new ListaEnlazada<>();
        for (int i = 0; i < cd.listar().getLength(); i++) {
            if (cd.listar().getInfo(i).getId_cursa().equals(id)) {
                aux.insertar(cd.listar().getInfo(i));
            }
        }
        return aux;
    }

    public static ListaEnlazada<Estudiante> recuperarEstudiantesCursa(Integer id) throws Exception {
        MatriculaControl mc = new MatriculaControl();
        ListaEnlazada<Estudiante> aux = new ListaEnlazada<>();
        ListaEnlazada<Matricula> listaMatriculas = mc.listar();

        EstudianteControl estudianteControl = new EstudianteControl();
        ListaEnlazada<Estudiante> listaEstudiantes = estudianteControl.getListaEstudiantes();

        for (int i = 0; i < listaMatriculas.getLength(); i++) {
            Matricula matricula = listaMatriculas.getInfo(i);
            if (matricula.getCodigo().equals(id)) {
                Estudiante estudiante = listaEstudiantes.getInfo(encontrarPosicion("estudiante", matricula.getId_estudiante()));
                aux.insertar(estudiante);
            }
        }
        return aux;
    }

    public static ListaEnlazada<Asistencia> generarAsistenciasporClase(ClaseDictada clase) throws Exception {

        ListaEnlazada<Asistencia> asistencias = new ListaEnlazada<>();
        ListaEnlazada<Estudiante> estudiantes = recuperarEstudiantesCursa(clase.getId_cursa());
        Integer ultimoId = new AsistenciaControl().all().getLength();

        for (int i = 0; i < estudiantes.getLength(); i++) {
            Asistencia asistencia = new Asistencia();
            asistencia.setEstadoAsistencia(true);
            asistencia.setId_estudiante(estudiantes.getInfo(i).getId());
            asistencia.setId_claseDictada(clase.getId());
            asistencia.setId(ultimoId + i + 1);
            asistencias.insertar(asistencia);
            asistencia = null;
        }
        estudiantes = null;
        return asistencias;
    }

    public static ListaEnlazada<Asistencia> recuperarAsistenciasClase(Integer idClase) throws Exception {
        AsistenciaControl ac = new AsistenciaControl();
        ListaEnlazada<Asistencia> asistencias = new ListaEnlazada<>();
        ListaEnlazada<Asistencia> aux = ac.all();

        for (int i = 0; i < aux.getLength(); i++) {
            Asistencia asistencia = aux.getInfo(i);
            if (asistencia.getId_claseDictada().equals(idClase)) {
                asistencias.insertar(asistencia);
            }
        }
        return asistencias;
    }

    public static Justificativo recuperarJustificativodeAsistencia(Integer k) {
        JustificativoControl jc = new JustificativoControl();
        for (int i = 0; i < jc.getListaJustificativos().getLenght(); i++) {
            try {
                if (jc.getListaJustificativos().getInfo(i).getId_asistencia().equals(k)) {
                    return jc.getListaJustificativos().getInfo(i);
                }
            } catch (Exception e) {
            }
        }
        return null;

    }

    public static ListaEnlazada<Matricula> recuperarMatriculasEstudiante(Integer es) {
        MatriculaControl mc = new MatriculaControl();
        ListaEnlazada<Matricula> aux = new ListaEnlazada<>();
        for (int i = 0; i < mc.getListaMatriculas().getLength(); i++) {
            try {
                if (mc.getListaMatriculas().getInfo(i).getId_estudiante().equals(es)) {
                    aux.insertar(mc.getListaMatriculas().getInfo(i));
                }
            } catch (Exception e) {

            }
        }
        return aux;
    }

    public static ListaEnlazada<Cursa> recuperarCursasEstudiante(Integer id) throws Exception {
        ListaEnlazada<Matricula> matri = recuperarMatriculasEstudiante(id);
        CursaControl cc = new CursaControl();
        ListaEnlazada<Cursa>aux = new ListaEnlazada<>();
        for (int i = 0; i < matri.getLength(); i++) {
            for (int j = 0; j < cc.getListaCursas().getLength(); j++) {
                if (cc.getListaCursas().getInfo(id).getId().equals(matri.getInfo(i).getId_cursa())) {
                    aux.insertar(cc.getListaCursas().getInfo(j));
                }
            }
        }
        return aux;
    }
    
    public static ListaEnlazada<Asistencia> recuperarAsistenciasEstudiante(Integer id, Integer cursa) throws Exception {
        AsistenciaControl ac = new AsistenciaControl();
        ListaEnlazada<Asistencia> aux = new ListaEnlazada<>();

        for (int i = 0; i < ac.getListaAsistencias().getLength(); i++) {
            Asistencia a = ac.getListaAsistencias().getInfo(i);
            ClaseDictadaControl cdc = new ClaseDictadaControl();
            ClaseDictada c = cdc.getListaClases().getInfo(Utiles.encontrarPosicion("clasedictada", a.getId_claseDictada()));
            if (a.getId_estudiante().equals(id) && c.getId_cursa().equals(cursa)){
                aux.insertar(a);
            }
        }
        return aux;
    }
    
}
