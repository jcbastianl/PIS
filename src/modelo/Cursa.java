/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;
import controlador.clases.CicloControl;
import java.util.Date;

/**
 *
 * @author jsbal
 */
public class Cursa {

    private Integer id;
    private Integer ciclo;
    private Docente docente;
    private Asignatura asignatura;
    private DynamicList<Matricula> matriculas;
    private Date fechaInicio;
    private Date fechaFin;

    public Cursa() {
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Boolean compare(Cursa p, String field, Integer type) {
        switch (type) {
            case 0:
                if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().toString().compareTo(p.getCiclo().toString()) < 0;
                } else if (field.equalsIgnoreCase("asignatura")) {
                    return getAsignatura().toString().compareTo(p.getAsignatura().toString()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() < p.getId().intValue());
                }
            case 1:
                if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().toString().compareTo(p.getCiclo().toString()) > 0;
                } else if (field.equalsIgnoreCase("asignatura")) {
                    return getAsignatura().toString().compareTo(p.getAsignatura().toString()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (getId().intValue() > p.getId().intValue());
                }
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        try {
            return getAsignatura() + " " + new CicloControl().getCiclos().getInfo(getCiclo()).toString();

        } catch (Exception e) {
            System.out.println("error desde modelo cursa " + e.getMessage());
            return "algo malo paso";
        }

    }

    /**
     * @return the matriculas
     */
    public DynamicList<Matricula> getMatriculas() {
        if (matriculas == null) {
            matriculas = new DynamicList<>();
        }
        return matriculas;
    }

    /**
     * @param matriculas the matriculas to set
     */
    public void setMatriculas(DynamicList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the ciclo
     */
    public Integer getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the asignatura
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * @param asignatura the asignatura to set
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

}
