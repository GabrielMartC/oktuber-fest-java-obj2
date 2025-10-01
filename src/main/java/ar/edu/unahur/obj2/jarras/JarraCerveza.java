package ar.edu.unahur.obj2.jarras;

import ar.edu.unahur.obj2.marcas.Marca;

public class JarraCerveza {
    private Double capacidad; //litros
    private Marca marcaCerveza;

    public JarraCerveza(Double capacidad, Marca marcaCerveza) {
        this.capacidad = capacidad;
        this.marcaCerveza = marcaCerveza;
    }

    //template
    public Double contenidoAlcoholico(){
        return (marcaCerveza.graduacionAlcoholica() * capacidad) / 100;
    }



    
}
