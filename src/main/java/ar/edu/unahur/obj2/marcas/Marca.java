package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.paises.Pais;

public abstract class Marca {
    protected Double cantLupulo;
    private final Pais paisOrigen;

    public Marca(Double cantLupulo, Pais paisOrigen) {
        this.cantLupulo = cantLupulo;
        this.paisOrigen = paisOrigen;
    }

    //strategy
    public abstract Double graduacionAlcoholica();

    public Double getCantLupulo() {
        return cantLupulo;
    }

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    

    



    
}
