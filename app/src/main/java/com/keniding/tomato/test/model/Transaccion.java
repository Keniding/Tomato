package com.keniding.tomato.test.model;

public class Transaccion {
    private String monto;
    private String tipo;
    private String concepto;
    private String aplicacion;
    private String id_usuario;

    public Transaccion(String monto, String tipo, String concepto, String aplicacion, String id_usuario) {
        this.monto = monto;
        this.tipo = tipo;
        this.concepto = concepto;
        this.aplicacion = aplicacion;
        this.id_usuario = id_usuario;
    }

    public Transaccion() {
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}