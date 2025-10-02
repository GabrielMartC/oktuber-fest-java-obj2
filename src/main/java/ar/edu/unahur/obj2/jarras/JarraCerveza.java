package ar.edu.unahur.obj2.jarras;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.Marca;

public class JarraCerveza {
    private Double capacidad; //litros
    private Marca marcaCerveza;
    private Carpa carpaDondeSeSirvio;

    public JarraCerveza(Double capacidad, Marca marcaCerveza, Carpa carpaDondeSeSirvio) {
        this.capacidad = capacidad;
        this.marcaCerveza = marcaCerveza;
        this.carpaDondeSeSirvio = carpaDondeSeSirvio;
    }

    //template
    public Double contenidoAlcoholico(){
        return (marcaCerveza.graduacionAlcoholica() * capacidad) / 100;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public Marca getMarcaCerveza() {
        return marcaCerveza;
    }

    public Carpa getCarpaDondeSeSirvio() {
        return carpaDondeSeSirvio;
    }

    

    


    
}
