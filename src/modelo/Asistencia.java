/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author jsbal
 */
public class Asistencia {
    private Integer id;
    private Estudiante estudiante;
    private ClaseDictada claseDictada;
    private Date fecha;
    private Integer id_horario;
    private Integer id_justificativo;
    private EstadoAsistencia estadoAsistencia;
    private Horario horario;
    

    public Asistencia() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the id_claseDictada
     */
    public ClaseDictada getClaseDictada() {
            if (claseDictada == null) {
                    claseDictada = new ClaseDictada();
            }
            return claseDictada;
    }

    /**
     * @param claseDictada the claseDictada to set
     */
    public void setClaseDictada(ClaseDictada claseDictada) {
        this.claseDictada = claseDictada;
    }

    /**
     * @return the id_horario
     */
    public Integer getId_horario() {
        return id_horario;
    }

    /**
     * @param id_horario the id_horario to set
     */
    public void setId_horario(Integer id_horario) {
        this.id_horario = id_horario;
    }

    /**
     * @return the id_justificativo
     */
    public Integer getId_justificativo() {
        return id_justificativo;
    }

    /**
     * @param id_justificativo the id_justificativo to set
     */
    public void setId_justificativo(Integer id_justificativo) {
        this.id_justificativo = id_justificativo;
    }

    /**
     * @return the estadoAsistencia
     */
    public EstadoAsistencia getEstadoAsistencia() {
            if (estadoAsistencia == null) {
                    estadoAsistencia = new EstadoAsistencia();
            }
            return estadoAsistencia;
    }

    /**
     * @param estadoAsistencia the estadoAsistencia to set
     */
    public void setEstadoAsistencia(EstadoAsistencia estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

        public Date getFecha() {
                return fecha;
        }

        public void setFecha(Date fecha) {
                this.fecha = fecha;
        }

        public Horario getHorario() {
                if (horario == null) {
                        horario = new Horario();
                }
                return horario;
        }

        public void setHorario(Horario horario) {
                this.horario = horario;
        }
        
        
}
