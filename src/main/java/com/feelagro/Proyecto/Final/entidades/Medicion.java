
package com.feelagro.Proyecto.Final.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Medicion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)       
    private Integer id;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private float humedadrelativa;
    private float temperatura;
    private float humedadsuelo1;
    private float humedadsuelo2;

    @ManyToOne
    private Dispositivos dispositivos;
    
    @OneToOne
    private Parametrizacion parametrizacion;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getHumedadrelativa() {
        return humedadrelativa;
    }

    public void setHumedadrelativa(float humedadrelativa) {
        this.humedadrelativa = humedadrelativa;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumedadsuelo1() {
        return humedadsuelo1;
    }

    public void setHumedadsuelo1(float humedadsuelo1) {
        this.humedadsuelo1 = humedadsuelo1;
    }

    public float getHumedadsuelo2() {
        return humedadsuelo2;
    }

    public void setHumedadsuelo2(float humedadsuelo2) {
        this.humedadsuelo2 = humedadsuelo2;
    }

    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    public Parametrizacion getParametrizacion() {
        return parametrizacion;
    }

    public void setParametrizacion(Parametrizacion parametrizacion) {
        this.parametrizacion = parametrizacion;
    }
    
    
}
