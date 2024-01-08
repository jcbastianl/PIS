/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Node;

/**
 *
 * @author jsbal
 */
public class Cursa {

    private String letra;
    private String aula;
    private Integer id;
    private Integer ciclo;
//    private Integer id_Docente;
    private Docente docente;
    private Integer id_asignatura;
    private DynamicList<Matricula> matriculas;

    /**
     * @return the letra
     */
    public Cursa() {
        matriculas = new DynamicList<>();
    }

    public String getLetra() {
        return letra;
    }

    /**
     * @param letra the letra to set
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    public DynamicList<Estudiante> obtenerEstudiantesMatriculados() {
        DynamicList<Estudiante> estudiantesMatriculados = new DynamicList<>();
        Node<Matricula> current = matriculas.getHeader();

        while (current != null) {
            estudiantesMatriculados.add(current.getInfo().getEstudiante());
            current = current.getNext();
        }

        return estudiantesMatriculados;
    }

    /**
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @param aula the aula to set
     */
    public void setAula(String aula) {
        this.aula = aula;
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

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(Integer id_asignatura) {
        this.id_asignatura = id_asignatura;
    }
    
    

    public Boolean compare(Cursa p, String field, Integer type) {
        switch (type) {
            case 0:
                if (field.equalsIgnoreCase("letra")) {
                    return getLetra().compareTo(p.getLetra()) < 0;
                } else if (field.equalsIgnoreCase("aula")) {
                    return getAula().compareTo(p.getAula()) < 0;
                } else if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().compareTo(p.getCiclo()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (id.intValue() < p.getId().intValue());
                }
            case 1:
                if (field.equalsIgnoreCase("letra")) {
                    return getLetra().compareTo(p.getLetra()) > 0;
                } else if (field.equalsIgnoreCase("aula")) {
                    return getAula().compareTo(p.getAula()) > 0;
                } else if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo().compareTo(p.getCiclo()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return (id.intValue() < p.getId().intValue());
                }
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return ciclo + " " + letra;
    }
}
