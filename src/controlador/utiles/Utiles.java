/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.utiles;

import controlador.TDA.listas.DynamicList;
import controlador.clases.AsignaturaControl;
import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstudianteControl;
import controlador.clases.MatriculaControl;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import modelo.Cursa;
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

    public static Integer encontrarPosicion(String tipoObj, Integer id) {

        try {

            switch (tipoObj) {
                case "docente":
                    DocenteControl dc = new DocenteControl();
                    for (int i = 0; i < dc.all().getLenght(); i++) {
                        if (Objects.equals(dc.all().getInfo(i).getId(), id)) {
                            return i;
                        }
                    }
                    break;
                case "matricula":
                    MatriculaControl cc = new MatriculaControl();
                    for (int i = 0; i < cc.all().getLenght(); i++) {
                        if (Objects.equals(cc.all().getInfo(i).getId(), id)) {
                            return i;
                        }
                    }
                case "estudiante":
                    EstudianteControl ec = new EstudianteControl();
                    for (int i = 0; i < ec.all().getLenght(); i++) {
                        if (Objects.equals(ec.all().getInfo(i).getId(), id)) {
                            return i;
                        }
                    }
                case "cursa":
                    CursaControl curc = new CursaControl();
                    for (int i = 0; i < curc.all().getLenght(); i++) {
                        if (Objects.equals(curc.all().getInfo(i).getId(), id)) {
                            return i;
                        }
                    }
                case "asignatura":
                    AsignaturaControl ac = new AsignaturaControl();
                    for (int i = 0; i < ac.all().getLenght(); i++) {
                        if (Objects.equals(ac.all().getInfo(i).getId(), id)) {
                            return i;
                        }
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
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

    public static Integer encontrarIndexSegunLista(DynamicList<Matricula> lista, Integer idBuscar) throws Exception{
        for (int i = 0; i < lista.getLenght(); i++) {
            if (Objects.equals(lista.getInfo(i).getId(), idBuscar)) {
                return i;
            }
        }
        return null;
    }
    
    public static Boolean identificarEstado(Integer i){
        if (i == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String traducirEstadoString(Boolean i){
        if (i) {
            return "APROBADO";
        } else {
            return "CANCELADO";
        }
    }
    
    public static Integer traducirEstadoIndice(Boolean i){
        if (i) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public static DynamicList<Cursa> identificarCursas(int u) throws Exception{
        CursaControl cc = new CursaControl();
        DynamicList<Cursa>aux = new DynamicList<>();
        for (int i = 0; i < cc.all().getLenght(); i++) {
            if (cc.all().getInfo(i).getCiclo() == u) {
                aux.add(cc.all().getInfo(i));
            }
        }
        return aux;
    }
    
}
