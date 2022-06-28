package com.example._20180252_20196044_lab10.Beans;

import java.util.Date;

public class Viaje {

    private int idViaje;
    private Date fechaViaje;
    private Float costounit;

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Float getCostounit() {
        return costounit;
    }

    public void setCostounit(Float costounit) {
        this.costounit = costounit;
    }
}
