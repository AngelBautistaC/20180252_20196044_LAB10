package com.example._20180252_20196044_lab10.Beans;

import java.util.Date;

public class Compra {

    private int idCompra;
    private String seguro;
    private Float gastototal;
    private int numtickets;
    private Date fechaReserva;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public Float getGastototal() {
        return gastototal;
    }

    public void setGastototal(Float gastototal) {
        this.gastototal = gastototal;
    }

    public int getNumtickets() {
        return numtickets;
    }

    public void setNumtickets(int numtickets) {
        this.numtickets = numtickets;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
