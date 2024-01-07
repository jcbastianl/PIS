/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.utiles;

import controlador.clases.CursaControl;
import controlador.clases.DocenteControl;
import controlador.clases.EstadoMatriculaControl;
import controlador.clases.EstudianteControl;
import controlador.clases.MatriculaControl;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;


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
                case "estadomatricula":
                    EstadoMatriculaControl emc = new EstadoMatriculaControl();
                    for (int i = 0; i < emc.all().getLenght(); i++) {
                        if (Objects.equals(emc.all().getInfo(i).getId(), id)) {
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
    
    
    //METODO PARA TRANSFORMAR UN LOCALDATE A DATE
    public static LocalDate DateALocal(Date fecha){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        return LocalDate.parse(dateFormat.format(fecha));
    }

    //METODO PARA TRANSFORMAR UN DATE A LOCALDATE   
    public static Date LocalADate(LocalDate fechaLocal){
        return Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    //METODO PARA DEVOLVER UN STRING DE UN DECIMAL CON UN FORMATO APTO PARA COMPARAR COMO STRING
    public static String decimalFormato(Double d){
        try {

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(d);
        } catch (IllegalArgumentException e) {

            System.err.println("Error: Formato inválido.");
            return ""; 
        }
    }
    
    public static String formaterarFecha(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date);
    }
    
    public static boolean compararTextoss(String text1, String text2){
        return (text1.equals(text2));
    
    }
    
}
