/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Admin
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "HibernateType")
public class Vehiculo implements Serializable {
    @Id
    @Column
    private String dominio;
    @Column
    private String modelo;
    @Column
    private String marca;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="Tipo")
    private Tipo coso;
    public enum Tipo {AUTO, MOTO};
    
    protected Vehiculo(String modelo, String marca, String dominio, Tipo coso){
        this.modelo = modelo;
        this.marca = marca;
        this.dominio = dominio;
        this.coso = coso;
    }

    public Vehiculo() {
    }

    @Override
    public String toString() {
        return getCoso() +"{" + "dominio=" + getDominio() + ", modelo=" + getModelo() + ", marca=" + getMarca() + '}';
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @return the dominio
     */
    public String getDominio() {
        return dominio;
    }
    
    public Tipo getTipo(){
    return getCoso();
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the coso
     */
    public Tipo getCoso() {
        return coso;
    }

    /**
     * @param coso the coso to set
     */
    public void setCoso(Tipo coso) {
        this.coso = coso;
    }
}
