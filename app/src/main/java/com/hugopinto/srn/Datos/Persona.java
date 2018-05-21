package com.hugopinto.srn.Datos;

public class Persona {
    private String carnet;
    private String nota;
    private String materia;
    private String catedratico;

    public Persona(){

    }
    public Persona(String carnet, String nota) {
        this.carnet = carnet;
        this.nota = nota;
    }


    public Persona(String carnet, String nota, String materia, String catedratico) {
        this.carnet = carnet;
        this.nota = nota;
        this.materia = materia;
        this.catedratico = catedratico;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }
}
