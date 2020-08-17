package com.uitienda.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eliezer
 */
public class ClienteModel implements Serializable{
   
    private Integer idCliente;   
    
    private String primerNombre;   
   
    private String segundoNombre;   
   
    private String primerApellido;   
    
    private String segundoApellido;   
    
    private String duiCliente;
    
    private String nitCliente;  
    
    private Integer flagEstado;   
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDuiCliente() {
        return duiCliente;
    }

    public void setDuiCliente(String duiCliente) {
        this.duiCliente = duiCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public Integer getFlagEstado() {
        return flagEstado;
    }

    public void setFlagEstado(Integer flagEstado) {
        this.flagEstado = flagEstado;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    @Override
    public String toString() {
        return "ClienteModel{" + "idCliente=" + idCliente + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", duiCliente=" + duiCliente + ", nitCliente=" + nitCliente + ", flagEstado=" + flagEstado + ", fechaCrea=" + fechaCrea + '}';
    }
    
    
}
